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
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import ph.fingra.statisticsweb.common.MemberRole;
import ph.fingra.statisticsweb.domain.Member;

public abstract class FingraphUserAuthorityUtils {
    
    private static final List<GrantedAuthority> ADMIN_ROLES
        = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    private static final List<GrantedAuthority> USER_ROLES
        = AuthorityUtils.createAuthorityList("ROLE_USER");
    
    public static Collection<? extends GrantedAuthority> createAuthorities(Member member) {
        if (MemberRole.valueOf(member.getRole()) == MemberRole.ROLE_ADMIN) {
            return ADMIN_ROLES;
        }
        return USER_ROLES;
    }
}
