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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ph.fingra.statisticsweb.common.MemberJoinstatus;
import ph.fingra.statisticsweb.common.MemberRole;
import ph.fingra.statisticsweb.common.MemberStatus;
import ph.fingra.statisticsweb.domain.Member;
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
        
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
        
        model.addAttribute("list", memberService.getList());
        
        return "manage/member";
    }
    
    //show detail of general user
    @RequestMapping(method = RequestMethod.GET, value = "/member/detail")
    public String memberDetail(@RequestParam("memberid") Integer memberid,
            @ActiveUser FingraphUser activeUser, Model model) {
        
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
    	
        model.addAttribute("member", memberService.get(memberid));
        
    	return "manage/memberDetail";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/member/form")
    public String memberForm(@RequestParam("memberid") Integer memberid,
            @ActiveUser FingraphUser activeUser, Model model) {
        
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
        
        model.addAttribute("member", memberService.get(memberid));
        
        return "manage/memberForm";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/member/edit")
    public String memberEdit(@ModelAttribute("member") Member member,
            @ActiveUser FingraphUser activeUser, Model model) {
        
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
        
        memberService.updateByAdmin(member);
        
        return "redirect:/manage/member";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/member/inactivate")
    public String memberDeactivate(@RequestParam("memberid") Integer memberid,
            @ActiveUser FingraphUser activeUser, Model model){
        
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
        
        Member member = new Member();
        member.setMemberid(memberid);
        member.setStatus(MemberStatus.DELETE.getValue());
        memberService.updateStatus(member);
        
        return "redirect:/manage/member";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/member/approval")
    public String memberApproval(@RequestParam("memberid") Integer memberid,
            @ActiveUser FingraphUser activeUser, Model model) {
    	
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
    	
        Member member = new Member();
        member.setMemberid(memberid);
        member.setJoinstatus(MemberJoinstatus.APPROVAL.getValue());
        memberService.updateJoinstatus(member);
        
        return "redirect:/manage/member";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/member/refuse")
    public String memberRefuse(@RequestParam("memberid") Integer memberid,
            @ActiveUser FingraphUser activeUser, Model model) {
        
        if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
            return "redirect:/common/error/permission";
        }
        
        Member member = new Member();
        member.setMemberid(memberid);
        member.setJoinstatus(MemberJoinstatus.REFUSE.getValue());
        memberService.updateJoinstatus(member);
        
        return "redirect:/manage/member";
    }
    
}
