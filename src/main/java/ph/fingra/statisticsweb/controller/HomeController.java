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

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ph.fingra.statisticsweb.common.MemberRole;
import ph.fingra.statisticsweb.domain.Member;
import ph.fingra.statisticsweb.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private MemberService memberService;
    
    // admin.properties test
    @Value("#{fingraphAdminAuth.email}")
    private String adminEmail;
    @Value("#{fingraphAdminAuth.name}")
    private String adminName;
    @Value("#{fingraphAdminAuth.password}")
    private String adminPassword;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);
        
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        
        String formattedDate = dateFormat.format(date);
        
        model.addAttribute("serverTime", formattedDate );
        
        // database & mybatis db mapper test
        model.addAttribute("memberlist", memberService.getList());
        
        // admin.properties test
        Member admin = new Member();
        admin.setUserid(adminEmail);
        admin.setName(adminName);
        admin.setPassword(adminPassword);
        admin.setRole(MemberRole.ROLE_ADMIN.getValue());
        model.addAttribute("admin", admin);
        
        return "test";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        
        return "home";
    }
}
