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

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class FingraphAuthenticationSuccessHandler
    extends SavedRequestAwareAuthenticationSuccessHandler {
    
    private String adminTargetUrl;
    private String userTargetUrl;
    
    public void setAdminTargetUrl(String adminTargetUrl) {
        this.adminTargetUrl = adminTargetUrl;
    }
    public void setUserTargetUrl(String userTargetUrl) {
        this.userTargetUrl = userTargetUrl;
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            //getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
        	setDefaultTargetUrl(adminTargetUrl);
        } else if (roles.contains("ROLE_USER")) {
            // getRedirectStrategy().sendRedirect(request, response, userTargetUrl);
            setDefaultTargetUrl(userTargetUrl);
        }
        super.onAuthenticationSuccess(request, response, authentication);
        return;
    }
}
