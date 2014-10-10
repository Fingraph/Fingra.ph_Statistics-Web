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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ph.fingra.statisticsweb.common.MemberStatus;
import ph.fingra.statisticsweb.domain.Member;
import ph.fingra.statisticsweb.service.MemberService;

@Component("fingraphUserService")
public class FingraphUserService implements UserDetailsService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
        //logger.debug("username is {}", username);
        Member member = memberService.get(username);
        if (member == null) {
            throw new UsernameNotFoundException("Not found user email");
        }
        
        if(MemberStatus.valueOf(member.getStatus()) == MemberStatus.DELETE){
            
        }
    
        FingraphUser userDetail = new FingraphUser(member);
        
        //logger.debug("userDetail is {}", userDetail);
        
        return userDetail;
    }
}
