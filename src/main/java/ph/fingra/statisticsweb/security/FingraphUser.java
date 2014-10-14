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

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import ph.fingra.statisticsweb.common.MemberJoinstatus;
import ph.fingra.statisticsweb.common.MemberStatus;
import ph.fingra.statisticsweb.domain.Member;

public class FingraphUser extends Member implements UserDetails {
    
    private static final long serialVersionUID = 6987027536752404716L;
    
    private boolean accountNonExpired = true;
    private boolean enabled = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    
    public FingraphUser(){};
    
    public FingraphUser(Member member) {
        setMemberid(member.getMemberid());
        setEmail(member.getEmail());
        setName(member.getName());
        setPassword(member.getPassword());
        setDepartment(member.getDepartment());
        setPhone(member.getPhone());
        setStatus(member.getStatus());
        setJoinstatus(member.getJoinstatus());
        setLastlogin(member.getLastlogin());
        setRole(member.getRole());
        if (MemberStatus.valueOf(member.getStatus()) != MemberStatus.ACTIVE) {
            this.accountNonLocked = false;
            this.enabled = false;
        }
        if (MemberJoinstatus.valueOf(member.getJoinstatus()) != MemberJoinstatus.APPROVAL) {
            this.accountNonLocked = false;
            this.enabled = false;
        }
    }
    public FingraphUser(Member member, PasswordEncoder passwordEncoder) {
        setMemberid(member.getMemberid());
        setEmail(member.getEmail());
        setName(member.getName());
        // encode password
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        setPassword(member.getPassword());
        setDepartment(member.getDepartment());
        setPhone(member.getPhone());
        setStatus(member.getStatus());
        setJoinstatus(member.getJoinstatus());
        setLastlogin(member.getLastlogin());
        setRole(member.getRole());
        if (MemberStatus.valueOf(member.getStatus()) != MemberStatus.ACTIVE) {
            this.accountNonLocked = false;
            this.enabled = false;
        }
        if (MemberJoinstatus.valueOf(member.getJoinstatus()) != MemberJoinstatus.APPROVAL) {
            this.accountNonLocked = false;
            this.enabled = false;
        }
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return FingraphUserAuthorityUtils.createAuthorities(this);
    }
    
    @Override
    public String getUsername() {
        return getEmail();
    }
    
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
