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
import ph.fingra.statisticsweb.domain.DayOfWeekData;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.TopCountryData;
import ph.fingra.statisticsweb.domain.TopResolutionData;
import ph.fingra.statisticsweb.domain.TopVersionsData;
import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.CountryService;
import ph.fingra.statisticsweb.service.DashBoardService;
import ph.fingra.statisticsweb.service.DistributionService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/distribution/*")
public class DistributionController extends BaseController {

	@Autowired
	private DistributionService distributionService;

	@Autowired
	private DashBoardService dashBoardService;

	@Autowired
	private CountryService countryService;

	/** include section data
	 * @param param
	 * @return
	 */
	private App distributionSectionInfo(DashBoardSearchParam param){
		App app = dashBoardService.getDistributionSectionInfo(param);
		return app;
	}

	/** Day Of Week
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/dayOfWeek/{appkey}")
	public String dayOfWeek(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",distributionSectionInfo(param));
		
		return "distribution/dayOfWeek";

	}

	/** Day Of Week Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getDayOfWeekAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String dayOfWeekAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = distributionService.getDayOfWeekFigures(searchParam);// most, least
		List<DayOfWeekData> list = distributionService.getDayOfWeekList(searchParam);
		paramMap.put("figures", figures);
		paramMap.put("list", list);
		return (new Gson()).toJson(paramMap);
	}

	/** Time Of Day
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/timeOfDay/{appkey}")
	public String timeOfDay(Model model,
						@PathVariable("appkey") String appkey,
						@RequestParam(required=false, defaultValue="1w", value="period") String period,
						DashBoardSearchParam param,
						@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",distributionSectionInfo(param));
		
		return "distribution/timeOfDay";

	}

	/** Time Of Day Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTimeOfDayAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String timeOfDayAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Figures figures = distributionService.getTimeOfDayFigures(searchParam);// most, least
		TimeOfDayData result = distributionService.getTimeOfDayList(searchParam);
		paramMap.put("figures", figures);
		paramMap.put("result", result);

		logger.info((new Gson()).toJson(paramMap));
		return (new Gson()).toJson(paramMap);
		
	}

	/** Top Resolution
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/topResolution/{appkey}")
	public String topResolution(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param,
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",distributionSectionInfo(param));
		
		return "distribution/topResolution";

	}

	/** Top Resolution Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTopResolutionAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTopResolutionAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		TopResolutionData tr = new TopResolutionData();//Resolution bar and timeseries

		if("daily".equals(searchParam.getTerm())){
			tr = distributionService.getResolutionDailyData(searchParam);
		}else if("weekly".equals(searchParam.getTerm())){
			tr = distributionService.getResolutionWeeklyData(searchParam);
		}else if("monthly".equals(searchParam.getTerm())){
			tr = distributionService.getResolutionMonthlyData(searchParam);
		}
		
		paramMap.put("tr", tr);
		return (new Gson()).toJson(paramMap);
		
	}


	/** Top Versions
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/topVersions/{appkey}")
	public String topVersions(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period,
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",distributionSectionInfo(param));
		
		return "distribution/topVersions";
		
	}

	/** Top Versions Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTopVersionsAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTopVersionsAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		TopVersionsData ov = new TopVersionsData();// pie and timeseries of osversion
		TopVersionsData av = new TopVersionsData();// pie and timeseries of appversion

		if("daily".equals(searchParam.getTerm())){
			ov = distributionService.getOsVesionsDailyData(searchParam);
			av = distributionService.getAppVesionsDailyData(searchParam);
		}else if("weekly".equals(searchParam.getTerm())){
			ov = distributionService.getOsVesionsWeeklyData(searchParam);
			av = distributionService.getAppVesionsWeeklyData(searchParam);
		}else if("monthly".equals(searchParam.getTerm())){
			ov = distributionService.getOsVesionsMonthlyData(searchParam);
			av = distributionService.getAppVesionsMonthlyData(searchParam);
		}
		paramMap.put("ov", ov);
		paramMap.put("av", av);
		return (new Gson()).toJson(paramMap);
	}

	/** Top Countries
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/topCountries/{appkey}")
	public String topCountries(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		model.addAttribute("app",distributionSectionInfo(param));
		
		return "distribution/topCountries";

	}

	/** Top Countries Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTopCountriesAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTopCountriesAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		TopCountryData cv = new TopCountryData();//country general(geo,area) data

		if("daily".equals(searchParam.getTerm())){
			if("sessions".equals(searchParam.getSegment())){
				cv = countryService.getCountrySessionsDailyData(searchParam);
			}else if("activeUsers".equals(searchParam.getSegment())){
				cv = countryService.getCountryActiveUsersDailyData(searchParam);
			}else if("sessionLength".equals(searchParam.getSegment())){
				cv = countryService.getCountrySessionLengthDailyData(searchParam);
			}else if("pageViews".equals(searchParam.getSegment())){
				cv = countryService.getCountryPageViewsDailyData(searchParam);
			}else if("newUsers".equals(searchParam.getSegment())){
				cv = countryService.getCountryNewUsersDailyData(searchParam);
			}
		}else if("weekly".equals(searchParam.getTerm())){
			if("sessions".equals(searchParam.getSegment())){
				cv = countryService.getCountrySessionsWeeklyData(searchParam);
			}else if("activeUsers".equals(searchParam.getSegment())){
				cv = countryService.getCountryActiveUsersWeeklyData(searchParam);
			}else if("sessionLength".equals(searchParam.getSegment())){
				cv = countryService.getCountrySessionLengthWeeklyData(searchParam);
			}else if("pageViews".equals(searchParam.getSegment())){
				cv = countryService.getCountryPageViewsWeeklyData(searchParam);
			}else if("newUsers".equals(searchParam.getSegment())){
				cv = countryService.getCountryNewUsersWeeklyData(searchParam);
			}
		}else if("monthly".equals(searchParam.getTerm())){
			if("sessions".equals(searchParam.getSegment())){
				cv = countryService.getCountrySessionsMonthlyData(searchParam);
			}else if("activeUsers".equals(searchParam.getSegment())){
				cv = countryService.getCountryActiveUsersMonthlyData(searchParam);
			}else if("sessionLength".equals(searchParam.getSegment())){
				cv = countryService.getCountrySessionLengthMonthlyData(searchParam);
			}else if("pageViews".equals(searchParam.getSegment())){
				cv = countryService.getCountryPageViewsMonthlyData(searchParam);
			}else if("newUsers".equals(searchParam.getSegment())){
				cv = countryService.getCountryNewUsersMonthlyData(searchParam);
			}
		}
		paramMap.put("cv", cv);
		return (new Gson()).toJson(paramMap);
	}

	/** section include
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
								DashBoardSearchParam param) {
		
		return "include/distributionSection";

	}

}
