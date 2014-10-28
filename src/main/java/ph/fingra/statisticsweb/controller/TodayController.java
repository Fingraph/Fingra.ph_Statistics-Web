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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.FrequencyData;
import ph.fingra.statisticsweb.domain.SessionLengthData;
import ph.fingra.statisticsweb.domain.TimeSeriesData;
import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.DashBoardService;
import ph.fingra.statisticsweb.service.TodayService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/today/*")
public class TodayController {

	@Autowired
	private TodayService todayService;	
	
	@Autowired
	private DashBoardService dashBoardService;
	
	private App TodaySectionInfo(DashBoardSearchParam param){
		App app = dashBoardService.getTodaySectionInfo(param);
		app.setAppkey(param.getAppkey());
		return app;
	}

	/**
	 * Today Common Section
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/include/section")
	public String includeSection(Model model, 
								@RequestParam("appkey") String appkey, 
								@RequestParam(required=false, defaultValue="1w", value="period") String period, 
								DashBoardSearchParam param){
		
		return "include/todaySection";
		
	}
	
	/** Today NewUser
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/newUsers/{appkey}")
	public String newUsers(Model model, 
							@PathVariable("appkey") String appkey, 
							@RequestParam(required=false, defaultValue="1w", value="period") String period,
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser){
		
		param.setPeriod(period);
		model.addAttribute("app", TodaySectionInfo(param));
		
		return "today/newUsers";
		
	}
	
	/** Today NewUser - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getNewUsersAjax", produces="text/plain;charset=UTF-8")
	public @ResponseBody String getNewUsersAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();
		figures = todayService.getNewUsersTimelyFigures(searchParam);
		list = todayService.getNewUsersTimelyInfoList(searchParam);
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		return (new Gson().toJson(paramMap));
	}
	
	/** Today ActiveUsers
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/activeUsers/{appkey}")
	public String activeUsers(Model model, 
							@PathVariable("appkey") String appkey, 
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser){
		
		param.setPeriod(period);
		model.addAttribute("app", TodaySectionInfo(param));
		return "today/activeUsers";
		
	}
	
	/** Today ActiveUsers - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getActiveUsersAjax", produces="text/plain;charset=UTF-8")
	public @ResponseBody String getActiveUsersAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam){		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();
		figures = todayService.getActiveUsersTimelyFigures(searchParam);
		list = todayService.getActiveUsersTimelyInfoList(searchParam);
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		return (new Gson().toJson(paramMap));
	}
	
	
	/** Today sessions
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/sessions/{appkey}")
	public String sessions(Model model, 
						@PathVariable("appkey") String appkey, 
						@RequestParam(required=false, defaultValue="1w", value="period") String period,
						DashBoardSearchParam param, 
						@ActiveUser FingraphUser activeUser){
		
		param.setPeriod(period);
		model.addAttribute("app", TodaySectionInfo(param));
		return "today/sessions";
		
	}
	
	/** Today sessions - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getSessionsAjax", produces="text/plain;charset=UTF-8")
	public @ResponseBody String getSessionsAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();
		figures = todayService.getSessionsTimelyfigures(searchParam); // figure
		list = todayService.getSessionTimelyInfoList(searchParam); // Line graph
		List<FrequencyData> slist = todayService.getSessionTimeSeriesList(searchParam); //time series session graph
		FrequencyData total = todayService.getFrequenceyTimelyRanges(searchParam); // Ranges graph
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		paramMap.put("slist", slist);
		paramMap.put("total", total);
		return (new Gson().toJson(paramMap));
	}
	
	
	/** Today sessionLength
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/sessionLength/{appkey}")
	public String sessionLength(Model model, 
								@PathVariable("appkey") String appkey, 
								@RequestParam(required=false, defaultValue="1w", value="period") String period,
								DashBoardSearchParam param, 
								@ActiveUser FingraphUser activeUser){
		
		param.setPeriod(period);
		model.addAttribute("app", TodaySectionInfo(param));
		return "today/sessionLength";
		
	}
	
	/** Today sessionLength - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getSessionLengthAjax", produces="text/plain;charset=UTF-8")
	public @ResponseBody String getSessionLengthAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();
		figures = todayService.getSessionLengthFigures(searchParam); //  figure
		list = todayService.getSessionLengthList(searchParam); // Line graph
		List<SessionLengthData> slist = todayService.getSessionLengthFrequenceyList(searchParam); // time series sessionLength graph
		SessionLengthData total = todayService.getSessionLengthFrequencey(searchParam); // Ranges graph
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		paramMap.put("slist", slist);
		paramMap.put("total", total);
		return (new Gson().toJson(paramMap));
	}
	
	
	/** Today PageViews
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pageViews/{appkey}")
	public String pageViews(Model model, 
						@PathVariable("appkey") String appkey,
						@RequestParam(required=false, defaultValue="1w", value="period") String period,
						DashBoardSearchParam param, 
						@ActiveUser FingraphUser activeUser){
		
		param.setPeriod(period);
		model.addAttribute("app", TodaySectionInfo(param));
		return "today/pageViews";
		
	}
	
	/** Today PageViews - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value="/getPageViewsAjax", produces="text/plain;charshet=UTF-8")
	public @ResponseBody String getPageViewsAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();
		figures = todayService.getPageViewsTimelyFigures(searchParam);
		list = todayService.getPageViewsTimelyInfoList(searchParam);
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		return (new Gson().toJson(paramMap));
	}
}
