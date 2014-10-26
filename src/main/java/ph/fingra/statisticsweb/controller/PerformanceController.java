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
import ph.fingra.statisticsweb.service.PerformanceService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/performance/*")
public class PerformanceController extends BaseController {
	
	@Autowired
	private PerformanceService performanceService;

	@Autowired
	private DashBoardService dashBoardService;

	private App performanceSectionInfo(DashBoardSearchParam param){
		App app = dashBoardService.getPerformanceSectionInfo(param);
		//logger.info("==================>:"+param.getAppkey());
		app.setAppkey(param.getAppkey());
		return app;
	}

	/** newUsers
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/newUsers/{appkey}")
	public String newUsers(Model model, 
						@PathVariable("appkey") String appkey,
						@RequestParam(required=false,defaultValue="1w",value="period") String period,
						DashBoardSearchParam param, 
						@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",performanceSectionInfo(param));
		return "performance/newUsers";
		
	}

	/** newUsers - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getNewUsersAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getNewUsersAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
			Figures figures = new Figures();
			List<TimeSeriesData> list = new ArrayList<>();

			if("daily".equals(searchParam.getTerm())){
				figures = performanceService.getNewUsersDailyFigures(searchParam);
				list = performanceService.getNewUsersDailyInfoList(searchParam);
			}else if("weekly".equals(searchParam.getTerm())){
				figures = performanceService.getNewUsersWeeklyFigures(searchParam);
				list = performanceService.getNewUsersWeeklyInfoList(searchParam);
			}else if("monthly".equals(searchParam.getTerm())){
				figures = performanceService.getNewUsersMonthlyFigures(searchParam);
				list = performanceService.getNewUsersMonthlyInfoList(searchParam);
			}

			paramMap.put("figures", figures);
			paramMap.put("list", list);
			return (new Gson()).toJson(paramMap);
	}

	/** activeUsers
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/activeUsers/{appkey}")
	public String activeUsers(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false,defaultValue="1w",value="period") String period, 
							DashBoardSearchParam param,
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",performanceSectionInfo(param));
		
		return "performance/activeUsers";
		
	}

	/** activeUsers - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getActiveUsersAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getActiveUsersAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();

		if("daily".equals(searchParam.getTerm())){
			figures = performanceService.getActiveUsersDailyFigures(searchParam);
			list = performanceService.getActiveUsersDailyInfoList(searchParam);
		}else if("weekly".equals(searchParam.getTerm())){
			figures = performanceService.getActiveUsersWeeklyFigures(searchParam);
			list = performanceService.getActiveUsersWeeklyInfoList(searchParam);
		}else if("monthly".equals(searchParam.getTerm())){
			figures = performanceService.getActiveUsersMonthlyFigures(searchParam);
			list = performanceService.getActiveUsersMonthlyInfoList(searchParam);
		}

		paramMap.put("result", true);
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		
		return (new Gson()).toJson(paramMap);
		
	}


	/** sessionLength
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/sessionLength/{appkey}")
	public String sessionLength(Model model, 
								@PathVariable("appkey") String appkey,
								@RequestParam(required=false, defaultValue="1w", value="period") String period, 
								DashBoardSearchParam param, 
								@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",performanceSectionInfo(param));
		
		return "performance/sessionLength";

	}

	/** sessionLength - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getSessionLengthAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String sessionLengthAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();
		List<SessionLengthData> slist = new ArrayList<>();
		SessionLengthData total = new SessionLengthData();

		if("daily".equals(searchParam.getTerm())){
			total = performanceService.getSessionLengthDailyTotal(searchParam);
			if("total".equals(searchParam.getSegment())){//areachart
				slist = performanceService.getSessionLengthDailySectionList(searchParam);
			}else if("median".equals(searchParam.getSegment())){//linechart
				list = performanceService.getSessionLengthDailyMedianList(searchParam);
			}
			figures = performanceService.getSessionLengthDailyFigures(searchParam);
		}else if("weekly".equals(searchParam.getTerm())){
			total = performanceService.getSessionLengthWeeklyTotal(searchParam);

			if("total".equals(searchParam.getSegment())){//areachart
				slist = performanceService.getSessionLengthWeeklySectionList(searchParam);
			}else if("median".equals(searchParam.getSegment())){
				list = performanceService.getSessionLengthWeeklyMedianList(searchParam);
			}
			figures = performanceService.getSessionLengthWeeklyFigures(searchParam);
		}else if("monthly".equals(searchParam.getTerm())){

			total = performanceService.getSessionLengthMonthlyTotal(searchParam);
			if("total".equals(searchParam.getSegment())){//areachart
				slist = performanceService.getSessionLengthMonthlySectionList(searchParam);
			}else if("median".equals(searchParam.getSegment())){
				list = performanceService.getSessionLengthMonthlyMedianList(searchParam);
			}
			figures = performanceService.getSessionLengthMonthlyFigures(searchParam);
		}

		paramMap.put("result", true);
		paramMap.put("total", total);
		paramMap.put("slist", slist);
		paramMap.put("list", list);
		paramMap.put("figures", figures);

		return (new Gson()).toJson(paramMap);
	}

	/** Sessions
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/sessions/{appkey}")
	public String sessions(Model model, 
						@PathVariable("appkey") String appkey,
						@RequestParam(required=false,defaultValue="1w",value="period") String period,
						DashBoardSearchParam param, 
						@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",performanceSectionInfo(param));
		
		return "performance/sessions";
		
	}

	/** Sessions - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getSessionsAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getSessionsAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		Map<String,Object> paramMap = new HashMap<String,Object>();

		Figures figures = performanceService.getSessionsFigures(searchParam); // basis on daily

		List<TimeSeriesData> list = new ArrayList<>();
		if ("daily".equals(searchParam.getTerm())) {
			FrequencyData total = performanceService.getFrequenceyDailyRanges(searchParam);
			paramMap.put("total", total);
			if("timeSeries".equals(searchParam.getSegment())){
				List<FrequencyData> slist = performanceService.getTimeSeriesSessionsDailyList(searchParam);
				paramMap.put("slist", slist);
			}else if("total".equals(searchParam.getSegment())){
				list = performanceService.getSessionsDailyList(searchParam);
				paramMap.put("list", list);
			}
		} else if("weekly".equals(searchParam.getTerm())) {
			FrequencyData total = performanceService.getFrequenceyWeeklyRanges(searchParam);
			paramMap.put("total", total);
			if("timeSeries".equals(searchParam.getSegment())){
				List<FrequencyData> slist = performanceService.getTimeSeriesSessionsWeeklyList(searchParam);
				paramMap.put("slist", slist);
			}else if("total".equals(searchParam.getSegment())){
				list = performanceService.getSessionsWeeklyList(searchParam);
				paramMap.put("list", list);
			}
		} else if("monthly".equals(searchParam.getTerm())) {
			FrequencyData total = performanceService.getFrequenceyMonthlyRanges(searchParam);
			paramMap.put("total", total);
			if ("timeSeries".equals(searchParam.getSegment())) {
				List<FrequencyData> slist = performanceService.getTimeSeriesSessionsMonthlyList(searchParam);
				paramMap.put("slist", slist);
			} else if("total".equals(searchParam.getSegment())) {
				list = performanceService.getSessionsMonthlyList(searchParam);
				paramMap.put("list", list);
			}
		}

		paramMap.put("figures", figures);
		return (new Gson()).toJson(paramMap);
	}
	
	/** pageViews
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pageViews/{appkey}")
	public String pageViews(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
	
		param.setPeriod(period);
		model.addAttribute("app",performanceSectionInfo(param));
		
		return "performance/pageViews";
	}

	/** pageViews - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getPageViewsAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getPageViewsAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = new Figures();
		List<TimeSeriesData> list = new ArrayList<>();

		if("daily".equals(searchParam.getTerm())){
			figures = performanceService.getPageViewsDailyFigures(searchParam);
			list = performanceService.getPageViewsDailyInfoList(searchParam);
		}else if("weekly".equals(searchParam.getTerm())){
			figures = performanceService.getPageViewsWeeklyFigures(searchParam);
			list = performanceService.getPageViewsWeeklyInfoList(searchParam);
		}else if("monthly".equals(searchParam.getTerm())){
			figures = performanceService.getPageViewsMonthlyFigures(searchParam);
			list = performanceService.getPageViewsMonthlyInfoList(searchParam);
		}

		paramMap.put("result", true);
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		return (new Gson()).toJson(paramMap);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/include/section")
	public String includeSection(Model model, 
								@RequestParam("appkey") String appkey,
								@RequestParam(required=false, defaultValue="1w", value="period") String period, 
								DashBoardSearchParam param) {

		return "include/performanceSection";

	}

}
