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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ph.fingra.statisticsweb.domain.Member;
import ph.fingra.statisticsweb.service.MemberService;

@Controller
public class SignupController extends BaseController {
    
    @Autowired
    private MemberService memberService;
    
    // admin.properties test
    @Value("#{fingraphAdminAuth.email}")
    private String adminEmail;
    @Value("#{fingraphAdminAuth.name}")
    private String adminName;
    @Value("#{fingraphAdminAuth.password}")
    private String adminPassword;
    
    @RequestMapping(method = RequestMethod.GET, value = "/signup/form")
    public String form(@ModelAttribute("member") Member member, Model model,
            HttpServletRequest request) {
        
        return "signup/form";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public String signup(@Valid Member member, BindingResult bindingResult,
            RedirectAttributes ra) {
        
        memberService.create(member);
        ra.addFlashAttribute("member", member);
        
        return "redirect:/signup/result";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/signup/result")
    public String signupResult(HttpServletRequest request, Member member) {
        
        if (member == null || member.getEmail() == null) {
            logger.debug("Reloaded! not support flash attribute member");
            return "redirect:/";
        }
        
        return "signup/result";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/signup/duplicateEmailCheck")
    public @ResponseBody String duplicateEmailCheck(@RequestParam("email") String email) {
        
        if (email.equals(adminEmail))
            return Boolean.toString(false);
        
        return Boolean.toString(!memberService.duplicateEmailCheck(email));
    }
}
