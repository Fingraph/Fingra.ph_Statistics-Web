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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class FingraphAuthenticationFailureHandler
    extends SimpleUrlAuthenticationFailureHandler {
    
    @SuppressWarnings("deprecation")
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        
        if (exception.getClass().isAssignableFrom(UnverifiedUserException.class)) {
            System.out.println(exception.getExtraInformation());
            getRedirectStrategy().sendRedirect(request, response,
                    "/login/unverified?email="+exception.getAuthentication().getName()
                    +"&status="+((FingraphUser)exception.getExtraInformation()).getStatus());
        } else if (exception.getClass().isAssignableFrom(PasswordMissmatchUserException.class)) {
            System.out.println(exception.getExtraInformation());
            getRedirectStrategy().sendRedirect(request, response,
                    "/login/form?error=100");
        } else if (exception.getClass().isAssignableFrom(UnapprovalUserException.class)) {
            System.out.println(exception.getExtraInformation());
            getRedirectStrategy().sendRedirect(request, response,
                    "/login/form?error=200");
        }
        else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
