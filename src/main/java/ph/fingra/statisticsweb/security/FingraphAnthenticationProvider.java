/**
 * Copyright 2014 tgrape Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ph.fingra.statisticsweb.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import ph.fingra.statisticsweb.common.MemberJoinstatus;
import ph.fingra.statisticsweb.common.MemberRole;
import ph.fingra.statisticsweb.common.MemberStatus;
import ph.fingra.statisticsweb.domain.Member;
import ph.fingra.statisticsweb.service.MemberService;

@SuppressWarnings("deprecation")
public class FingraphAnthenticationProvider
    extends AbstractUserDetailsAuthenticationProvider {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";
    
    private PasswordEncoder passwordEncoder;
    // password encoder for admin.properties
    private org.springframework.security.crypto.password.PasswordEncoder adminPasswordEncoder;
    
    private String userNotFoundEncodedPassword;
    
    private SaltSource saltSource;
    
    private MemberService memberService;
    
    // admin.properties values
    @Value("#{fingraphAdminAuth.email}")
    private String adminEmail;
    @Value("#{fingraphAdminAuth.name}")
    private String adminName;
    @Value("#{fingraphAdminAuth.password}")
    private String adminPassword;
    
    public FingraphAnthenticationProvider() {
        setPasswordEncoder(new PlaintextPasswordEncoder());
    }
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        
        Object salt = null;
        
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }
        
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            
            throw new BadCredentialsException(
                    messages.getMessage("Invalid user id or password. Please try again.", "Bad credentials"),
                    userDetails);
        }
        
        String presentedPassword = authentication.getCredentials().toString();
        logger.debug("userDetails {}, presentedPassword {}", userDetails,presentedPassword);
        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
            logger.debug("Authentication failed: password does not match stored value");
            
            throw new BadCredentialsException(
                    messages.getMessage("Invalid user id or password. Please try again.", "Bad credentials"),
                    userDetails);
        }
        
        FingraphUser member = (FingraphUser) userDetails;
        if (MemberStatus.valueOf(member.getStatus()) != MemberStatus.ACTIVE
                || MemberJoinstatus.valueOf(member.getJoinstatus()) != MemberJoinstatus.APPROVAL){
            logger.debug("Authentication failed: un-verified user");
            throw new UnverifiedUserException("AbstractUserDetailsAuthenticationProvider.disabled", userDetails);
        }
    }
    
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.memberService, "A MemberService must be set");
        Assert.notNull(this.passwordEncoder, "A PasswordEncoder must be set");
    }
    
    @SuppressWarnings("unused")
    @Override
    protected UserDetails retrieveUser(String username,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        
        // admin.properties values
        logger.debug("[adminEmail] {}", adminEmail);
        logger.debug("[adminName] {}", adminName);
        logger.debug("[adminPassword] {}", adminPassword);
        
        UserDetails loadedUser=null;
        try {
            logger.debug("retrieveUser {}", username);
            
            Member member = null;
            if (username.equals(adminEmail)) {
                member = new Member();
                member.setUserid(adminEmail);
                member.setName(adminName);
                member.setPassword(adminPassword);
                member.setStatus(MemberStatus.ACTIVE.getValue());
                member.setJoinstatus(MemberJoinstatus.APPROVAL.getValue());
                member.setRole(MemberRole.ROLE_ADMIN.getValue());
            }
            else {
                member = memberService.get(username);
            }
            if (member == null) {
                throw new UsernameNotFoundException("Not found user id");
            }
            
            // lastlogin update
            if (member.getRole() == MemberRole.ROLE_USER.getValue()) {
                memberService.updateMemberLastLoginTime(member);
            }
            
            if (member.getRole() == MemberRole.ROLE_ADMIN.getValue()) {
                //logger.debug("passwordEncoder {}", adminPasswordEncoder);
                loadedUser = new FingraphUser(member, adminPasswordEncoder);
            }
            else {
                loadedUser = new FingraphUser(member);
            }
            logger.debug("userDetail is {}", loadedUser.toString());
        } catch (UsernameNotFoundException notFound) {
            if (authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials().toString();
                passwordEncoder.isPasswordValid(userNotFoundEncodedPassword, presentedPassword, null);
            }
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        
        if (loadedUser == null) {
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        
        return loadedUser;
    }
    
    @Autowired public void setPasswordEncoder(Object passwordEncoder) {
        
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        
        if (passwordEncoder instanceof PasswordEncoder) {
            setPasswordEncoder((PasswordEncoder) passwordEncoder);
            return;
        }
        
        if (passwordEncoder instanceof org.springframework.security.crypto.password.PasswordEncoder) {
            final org.springframework.security.crypto.password.PasswordEncoder delegate
                = (org.springframework.security.crypto.password.PasswordEncoder) passwordEncoder;
            
            // password encoder for admin.properties
            adminPasswordEncoder = delegate;
            
            setPasswordEncoder(new PasswordEncoder() {
                public String encodePassword(String rawPass, Object salt) {
                    checkSalt(salt);
                    return delegate.encode(rawPass);
                }
                
                public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
                    checkSalt(salt);
                    return delegate.matches(rawPass, encPass);
                }
                
                private void checkSalt(Object salt) {
                    Assert.isNull(salt, "Salt value must be null when used with crypto module PasswordEncoder");
                }
            });
            
            return;
        }
        
        throw new IllegalArgumentException("passwordEncoder must be a PasswordEncoder instance");
    }
    
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.userNotFoundEncodedPassword = passwordEncoder.encodePassword(USER_NOT_FOUND_PASSWORD, null);
        this.passwordEncoder = passwordEncoder;
    }
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
    
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }
    protected SaltSource getSaltSource() {
        return saltSource;
    }
    
    public MemberService getMemberService() {
        return memberService;
    }
    @Autowired protected void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
