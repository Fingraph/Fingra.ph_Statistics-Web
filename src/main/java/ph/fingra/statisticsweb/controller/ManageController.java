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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.MemberService;

@Controller
@RequestMapping({"/manage/*"})
public class ManageController extends BaseController {
    
    @Autowired
    private MemberService memberService;
    
    //lookup list of general users
    @RequestMapping(method = RequestMethod.GET, value = "/member")
    public String member(@ActiveUser FingraphUser activeUser, Model model) {
        
        // TODO:
        
        return "manage/member";
    }
    
    //show detail of general user
    @RequestMapping(method = RequestMethod.GET, value = "/member/detail")
    public String memberDetail(@ActiveUser FingraphUser activeUser, Model model) {
    
    	// TODO;
    	
    	return "manage/member/detail";
    }
    
    //Password Reset Confirmation
    @RequestMapping(method = RequestMethod.POST, value = "/member/resetConfirm")
    public String memberResetConfirm(@ActiveUser FingraphUser activeUser, Model model){
    	
    	// TODO;
    	
    	return "manage/member/resetConfirm";
    	
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/member/signupConfirm")
    public String memberSignupConfirm(@ActiveUser FingraphUser activeUser, Model model) {
    	
    	// TODO;
    	
    	return "manage/member/signupConfirm";
    	
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/member/deactivate")
    public String memberDeactivate(@ActiveUser FingraphUser activeUser, Model model){
    	
    	// TODO;
    	
    	return "manage/member/deactivate";
    	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/member/create")
    public String memberCreateForm(@ActiveUser FingraphUser activeUser, Model model){
    	
    	// TODO;
    	
    	return "manage/member/create";
    	
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/member/create")
    public String memberCreate(@ActiveUser FingraphUser activeUser, Model model) {
    	
    	// TODO;
    	
    	return "manage/member/create";
    }
    
}
