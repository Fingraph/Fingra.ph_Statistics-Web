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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ph.fingra.statisticsweb.domain.Member;
import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.CodeService;
import ph.fingra.statisticsweb.service.MemberService;

@Controller
@RequestMapping({"/account/*"})
public class AccountController extends BaseController {
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
    private MemberService memberService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editForm(@ActiveUser FingraphUser activeUser, Model model) {
        
        model.addAttribute("countries", codeService.getAllCountries());
        model.addAttribute("member", memberService.get(activeUser.getMemberid()));
        
        return "account/editForm";
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/edit")
	public String edit(@ModelAttribute("member") Member member, @ActiveUser FingraphUser activeUser) {

		member.setMemberid(activeUser.getMemberid());
		memberService.update(member);
		
		return "redirect:/app/list";
		
	}
    
	@RequestMapping(method = RequestMethod.GET, value = "/resetPassword") 
	public String resetPasswordForm() {
		return "account/resetPassword";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/resetPassword")
	public String resetPassword(@RequestParam("userid") String userid, RedirectAttributes ra) {
		
		if (!memberService.duplicateUseridCheck(userid)) {
			return "redirect:/account/resetPassword?error=" + userid;
		}
		memberService.resetPassword(userid);
		ra.addFlashAttribute("email", userid);
		return "redirect:/account/resetPasswordResult";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/signup")
	public String signupForm(@ModelAttribute("member") Member member, Model model) {

		model.addAttribute("countries", codeService.getAllCountries());
		
		return "account/signupForm";

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/signup") 
	public String signup(@Valid Member member, BindingResult bindingResult, RedirectAttributes ra) {
		
		memberService.create(member);
		ra.addFlashAttribute("member", member);
		
		return "redirect:/signup/result";
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/signup/result")
	public String signupResult(Member member){
		
		if (member == null || member.getUserid() == null) {
			logger.debug("Reloaded! not suppport flash attribute member");
			return "redirect:/";
		}
		
		return "signup/result";
		
	}

}
