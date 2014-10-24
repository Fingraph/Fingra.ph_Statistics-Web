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

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;

@Controller
@RequestMapping("/common/*")
public class CommonController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping(value = "/getPeriedAjax", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public @ResponseBody
	    String getPeriedAjax(@RequestParam(value = "peried", defaultValue = "30d") String peried, @RequestParam(value = "term", defaultValue = "daily") String term) {
	
		logger.info("peried=" + peried);
		logger.info("term=" + term);
		Gson gson = new Gson();
		String result = gson.toJson("");
		logger.info("date from to is {}", result);
		return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/changeLocaleByAjax")
    public @ResponseBody
	    String changeLocale(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		Locale locale = null;
		if (lang.equals("ko")) {
		    locale = Locale.KOREA;
		} else if (lang.equals("cn")) {
		    locale = Locale.CHINA;
		} else if (lang.equals("hk")) {
		    locale = Locale.TRADITIONAL_CHINESE;
		} else if (lang.equals("ja")){
			locale = Locale.JAPAN;
	    } else {
		    locale = Locale.ENGLISH;
		}
	
		localeResolver.setLocale(request, response, locale);
	
		return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getLocaleByAjax")
    public @ResponseBody
	    String changeLocale(HttpServletRequest request, HttpServletResponse response) {
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		String lang = null;
		try {
		    lang = localeResolver.resolveLocale(request).getISO3Country();
		} catch (Exception e) {
		    lang = null;
		}
	
		lang = lang == null ? "eng" : localeResolver.resolveLocale(request).getISO3Country();
		return lang.trim();
    }

    @RequestMapping(value = "/error/404")
    public String error404(Model model) {
    	return "error/404Error";
    }

    @RequestMapping(value = "/error/500")
    public String error500(Model model) {
    	return "error/500Error";
    }

    @RequestMapping(value = "/error/permission")
    public String errorPermission(Model model) {
    	return "error/permissionError";
    }
}
