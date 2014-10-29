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

import ph.fingra.statisticsweb.common.MemberRole;
import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.AppService;

@Controller
public class AppController extends BaseController {
    
    @Autowired
    private AppService appService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/app/list")
    public String appList(@ActiveUser FingraphUser activeUser, Model model) {
        
        DashBoardSearchParam param = new DashBoardSearchParam();
        model.addAttribute("list", appService.getAppList(param));
        
        return "app/list";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "app/form")
    public String registForm(@ModelAttribute("app") App app, @ActiveUser FingraphUser activeUser) {
    	
    	if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
    		return "redirect:/common/error/permission";
    	}
    	
    	
    	return "app/form";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/app/regist") 
    public String regist(@ActiveUser FingraphUser activeUser, App app){
    	
    	if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
    		return "redirect:/common/error/permission";
    	}
    	
    	appService.create(app);
    	
    	return "redirect:/app/list";    	
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "app/edit")
    public String editForm(@RequestParam("appkey") String appkey, Model model, @ActiveUser FingraphUser activeUser) {

    	if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
    		return "redirect:/common/error/permission";
    	}

    	App app = appService.get(appkey);
    	model.addAttribute("app", app);
    	return "app/edit";
    	
    }

    @RequestMapping(method = RequestMethod.POST, value = "/app/edit")
    public String edit(@ActiveUser FingraphUser activeUser, App app) { 
    	
    	if (activeUser.getRole() != MemberRole.ROLE_ADMIN.getValue()) {
    		return "redirect:/common/error/permission";
    	}
    	
    	appService.update(app);

    	return "redirect:/app/list";
    }
    
}
