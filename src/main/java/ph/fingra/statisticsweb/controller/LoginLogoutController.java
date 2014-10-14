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

package ph.fingra.statisticsweb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.MemberService;

@Controller
public class LoginLogoutController extends BaseController {
    
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/login/form")
    public String loginForm() {
        return "login";
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(HttpSession session) {
        FingraphUser userDetails = (FingraphUser)session.getAttribute("userLoginInfo");
        
        logger.debug("Welcome logout! {}, {}", session.getId(), userDetails.getUsername());
        
        session.invalidate();
    }
    
}
