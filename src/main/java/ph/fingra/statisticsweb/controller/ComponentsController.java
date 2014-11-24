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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ph.fingra.statisticsweb.domain.ActualData;
import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.Component;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.ComponentsManage;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.MsDropDown;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.TopCountriesData;
import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.ComponentsService;
import ph.fingra.statisticsweb.service.DashBoardService;

import com.google.gson.Gson;

@Controller
@RequestMapping("/components/*")
public class ComponentsController {
	
	@Autowired
	private ComponentsService componentsService;

	@Autowired
	private DashBoardService dashBoardService;

	/** include section data
	 * @param param
	 * @return
	 */
	private App componentsSectionInfo(DashBoardSearchParam param){
		App app = dashBoardService.getComponentsSectionInfo(param);
		return app;
	}

	/** New Users
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
						@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		App app = componentsSectionInfo(param);
		model.addAttribute("app",app);
		
		return "components/newUsers";

	}

	/**NewUsersAjax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getNewUsersAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getNewUsersAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
			//tlist = term(mybatis 'where' cotext).
			List<Components> tlist = componentsService.getNewUsersTotalList(searchParam);
			//alist are list of the actual data.
			List<ActualData> alist = componentsService.getNewUsersActualDataList(searchParam);

			List<String> eventkeylist = new ArrayList<>();
			List<Integer> groupkeylist = new ArrayList<>();
			if(tlist.size()>0){
				if(searchParam.getGroupkey()== -1){//intergroup
					//put groupkeys to parameters.
					for(Components cp: tlist){
						groupkeylist.add(cp.getGroupkey());
					}
					searchParam.setTopNGrpList(groupkeylist);

					List<HashMap> tslist = new ArrayList<>();
					if("daily".equals(searchParam.getTerm())){
						tslist = componentsService.getNewUsersInterGroupDailyTimeSeriesList(searchParam);
					}else if("weekly".equals(searchParam.getTerm())){
						tslist = componentsService.getNewUsersInterGroupWeeklyTimeSeriesList(searchParam);
					}else if("monthly".equals(searchParam.getTerm())){
						tslist = componentsService.getNewUsersInterGroupMonthlyTimeSeriesList(searchParam);
					}
					paramMap.put("tslist", tslist);
				}else{
					//put componentkeys to parameters.
					for(Components cp: tlist){
						eventkeylist.add(cp.getComponentkey());
					}
					searchParam.setTopNList(eventkeylist);

					List<HashMap> tslist = new ArrayList<>();
						if("daily".equals(searchParam.getTerm())){
							tslist = componentsService.getNewUsersDailyTimeSeriesList(searchParam);
						}else if("weekly".equals(searchParam.getTerm())){
							tslist = componentsService.getNewUsersWeeklyTimeSeriesList(searchParam);
						}else if("monthly".equals(searchParam.getTerm())){
							tslist = componentsService.getNewUsersMonthlyTimeSeriesList(searchParam);
						}
					paramMap.put("tslist", tslist);
				}
			}
			paramMap.put("tlist", tlist);
			paramMap.put("alist", alist);
			return (new Gson()).toJson(paramMap).toString();
	}


	/** Active Users
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
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		App app = componentsSectionInfo(param);
		model.addAttribute("app",app);

		return "components/activeUsers";
		
	}

	/** ActiveUsersAjax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getActiveUsersAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getActiveUsersAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
			//tlist = term(mybatis 'where' cotext).
			List<Components> tlist = componentsService.getActiveUsersTotalList(searchParam);
			//alist are list of the actual data.
			List<ActualData> alist = componentsService.getActiveUsersActualDataList(searchParam);
			List<String> eventkeylist = new ArrayList<>();
			List<Integer> groupkeylist = new ArrayList<>();
			if(tlist.size()>0){
				if(searchParam.getGroupkey()== -1){//intergroup
					//put groupkeys to parameters.
					for(Components cp: tlist){
						groupkeylist.add(cp.getGroupkey());
					}
					searchParam.setTopNGrpList(groupkeylist);

					List<HashMap> tslist = new ArrayList<>();
					if("daily".equals(searchParam.getTerm())){
						tslist = componentsService.getActiveUsersInterGroupDailyTimeSeriesList(searchParam);
					}else if("weekly".equals(searchParam.getTerm())){
						tslist = componentsService.getActiveUsersInterGroupWeeklyTimeSeriesList(searchParam);
					}else if("monthly".equals(searchParam.getTerm())){
						tslist = componentsService.getActiveUsersInterGroupMonthlyTimeSeriesList(searchParam);
					}
				paramMap.put("tslist", tslist);
				}else{
					//put componentkeys to parameters.
					for(Components cp: tlist){
						eventkeylist.add(cp.getComponentkey());
					}
					searchParam.setTopNList(eventkeylist);
					List<HashMap> tslist = new ArrayList<>();
						if("daily".equals(searchParam.getTerm())){
							tslist = componentsService.getActiveUsersDailyTimeSeriesList(searchParam);
						}else if("weekly".equals(searchParam.getTerm())){
							tslist = componentsService.getActiveUsersWeeklyTimeSeriesList(searchParam);
						}else if("monthly".equals(searchParam.getTerm())){
							tslist = componentsService.getActiveUsersMonthlyTimeSeriesList(searchParam);
						}
					paramMap.put("tslist", tslist);
				}
			}
			paramMap.put("tlist", tlist);
			paramMap.put("alist", alist);
			return (new Gson()).toJson(paramMap);
	}

	/** Page Views
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
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		App app = componentsSectionInfo(param);
		model.addAttribute("app",app);

		return "components/pageViews";
		
	}

	/** PageViewsAjax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getPageViewsAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getPageViewsAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
			//tlist = term(mybatis 'where' cotext).
			List<Components> tlist = componentsService.getPageViewsTotalList(searchParam);
			//alist are list of the actual data.
			List<ActualData> alist = componentsService.getPageViewsActualDataList(searchParam);

			List<String> eventkeylist = new ArrayList<>();
			List<Integer> groupkeylist = new ArrayList<>();
			if(tlist.size()>0){
				if(searchParam.getGroupkey()== -1){//intergroup
					//put groupkeys to parameters.
					for(Components cp: tlist){
						groupkeylist.add(cp.getGroupkey());
					}
					searchParam.setTopNGrpList(groupkeylist);

					List<HashMap> tslist = new ArrayList<>();
					if("daily".equals(searchParam.getTerm())){
						tslist = componentsService.getPageViewsInterGroupDailyTimeSeriesList(searchParam);
					}else if("weekly".equals(searchParam.getTerm())){
						tslist = componentsService.getPageViewsInterGroupWeeklyTimeSeriesList(searchParam);
					}else if("monthly".equals(searchParam.getTerm())){
						tslist = componentsService.getPageViewsInterGroupMonthlyTimeSeriesList(searchParam);
					}
				paramMap.put("tslist", tslist);
				}else{
					//put componentkeys to parameters.
					for(Components cp: tlist){
						eventkeylist.add(cp.getComponentkey());
					}
					searchParam.setTopNList(eventkeylist);
					List<HashMap> tslist = new ArrayList<>();
						if("daily".equals(searchParam.getTerm())){
							tslist = componentsService.getPageViewsDailyTimeSeriesList(searchParam);
						}else if("weekly".equals(searchParam.getTerm())){
							tslist = componentsService.getPageViewsWeeklyTimeSeriesList(searchParam);
						}else if("monthly".equals(searchParam.getTerm())){
							tslist = componentsService.getPageViewsMonthlyTimeSeriesList(searchParam);
						}
					paramMap.put("tslist", tslist);
				}
			}
			paramMap.put("tlist", tlist);
			paramMap.put("alist", alist);
			return (new Gson()).toJson(paramMap);
	}

	/** Time Of Day
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/timeOfDay/{appkey}")
	public String timeOfDay(Model model,
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		App app = componentsSectionInfo(param);
		model.addAttribute("app",app);

		return "components/timeOfDay";
	}

	/** Time of Day Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTimeOfDayAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTimeOfDayAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		// This data are composed with summation value. It will be proceed by each term on 'where' context of the mybatis.

		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<TimeOfDayData> tlist = componentsService.getTimeOfDayTotalList(searchParam);
		// Get time of day to make table
		String maxTime = "";
		if(tlist.size()>0){
			maxTime = componentsService.getTimeOfDayMaxTime(searchParam);
		}

		paramMap.put("tlist", tlist);
		paramMap.put("maxTime", maxTime);
		return (new Gson()).toJson(paramMap);
	}

	/**  Time of Day Actual Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTimeOfDayActualDataAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTimeOfDayActualDataAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		// This data are composed with summation value. It will be proceed by each term on 'where' context of the mybatis.

		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<ActualData> alist = componentsService.getTimeOfDayActualDataList(searchParam);
		if(alist == null){
			ActualData adata = new ActualData();
			alist.add(adata);
		}
		paramMap.put("alist", alist);
		return (new Gson()).toJson(paramMap);
	}

	/** Top Countries
	 * @param model
	 * @param appkey
	 * @param period
	 * @param param
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/topCountries/{appkey}")
	public String topCountries(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w", value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
	
		param.setPeriod(period);
		App app = componentsSectionInfo(param);
		model.addAttribute("app",app);
		
		return "components/topCountries";
	}

	/** Top Countries Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTopCountriesAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTopCountriesAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		// Make TOP5 accumulation chart and select box of counties.

		TopCountriesData data = new TopCountriesData();
		data = componentsService.getTopCountriesData(searchParam);
		return (new Gson()).toJson(data);
	}

	/**  Top Countries Actual Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getTopCountriesActualDataAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getTopCountriesActualDataAjax(@ModelAttribute("fingraphSearchParam") FingraphSearchParam searchParam) {
		// This data are composed with summation value. It will be proceed by each term on 'where' context of the mybatis.  

		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<ActualData> alist = componentsService.getTopCountriesActualDataList(searchParam);
		paramMap.put("alist", alist);
		return (new Gson()).toJson(paramMap);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/manage/{appkey}")
	public String manage(Model model, 
						@PathVariable("appkey") String appkey,
						@RequestParam(required=false,defaultValue="1w",value="period") String period, 
						DashBoardSearchParam param,
						@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);

		List<ComponentsManage> list = componentsService.getComponentsManageList(param);
		model.addAttribute("list",list);
		
		//sectionInfo
		App app = componentsSectionInfo(param);
		model.addAttribute("app",app);
		return "components/manage";
	}

	@RequestMapping(value = "/addHtmlAjax", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public String addHtmlAjax(Model model,
    						@ModelAttribute("event") Component event,
    						@ActiveUser FingraphUser activeUser) 
    						throws Exception{
		
		int duplicateCnt = componentsService.getComponentCountByName(event);
		if (duplicateCnt > 0) {
			throw new Exception("A name of component can not be duplicated!");
		}
		
		Component newEvent = componentsService.addComponentWithGroup(event);

		model.addAttribute("type", "addComponentResult");
		model.addAttribute("event", newEvent);
		
		return "components/componentsAjaxHtml";
		
	}

	/** edit Ajax
	 * @param event
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(value = "/editAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String editAjax(@ModelAttribute("Event") Component event,
    									@ActiveUser FingraphUser activeUser) 
										throws Exception{
		
		String eventkey ="";
		
		if(!"".equals(event.getComponentname())){
			int duplicateCnt = componentsService.getComponentCountByName(event);
			if (duplicateCnt > 0) {
				throw new Exception("A name of component can not be duplicated!");
			}
			
			componentsService.editComponent(event);
			eventkey = event.getComponentkey();
		}

		return (new Gson()).toJson(true);
	}

	/** remove Ajax
	 * @param event
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(value = "/removeAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String removeAjax(@ModelAttribute("event") Component event,@ActiveUser FingraphUser activeUser) {
		
		String eventkey ="";
		if(!"".equals(event.getComponentkey())){
			componentsService.removeComponent(event);
			eventkey = event.getComponentkey();
		}

		return (new Gson()).toJson(true);
		
	}

	//removeMapAjax
	@RequestMapping(value = "/removeMapAjax", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public @ResponseBody String removeMapAjax(@RequestParam("ieventkey[]") String [] ieventkey,
    										@RequestParam("appkey") String appkey,
    										@ActiveUser FingraphUser activeUser) {
		
		HashMap map = new HashMap();
		map.put("appkey", appkey);
		map.put("icomponentkey", ieventkey);
		componentsService.removeArrayComponent(map);
		return (new Gson()).toJson(true);
		
	}
	
	//moveMapAjax
	@RequestMapping(value = "/moveMapAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String removeMapAjax(@RequestParam("ieventkey[]") String [] ieventkey,
								    		@RequestParam("appkey") String appkey,
								    		@RequestParam("groupkey") int groupkey,
								    		@ActiveUser FingraphUser activeUser) {
		
		if (ieventkey.length>0) {
			for(int i=0;i<ieventkey.length;i++){
				String[] output = ieventkey[i].split("_");
				ieventkey[i]= output[1];
			}

			HashMap map = new HashMap();
			map.put("appkey", appkey);
			map.put("groupkey", groupkey);
			map.put("icomponentkey", ieventkey);
			componentsService.moveArrayComponent(map);
			return (new Gson()).toJson(true);
		} else {
			return (new Gson()).toJson(false);
		}

	}



	//getGroupListAjax
	@RequestMapping(value = "/getGroupListAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getGroupListAjax(@RequestParam("appkey") String appkey,
    											@ActiveUser FingraphUser activeUser) {
		
		List<ComponentsGroup> glist = componentsService.getComponentsGroupList(appkey);
		List<MsDropDown> list = new ArrayList<>();
		if(glist.size()>0){
			for(ComponentsGroup group: glist){
				MsDropDown ms = new MsDropDown();
				ms.setValue(group.getGroupkey()+"");
				ms.setText(group.getGroupname());
				list.add(ms);
			}
		}
		
		return (new Gson()).toJson(list);
		
	}

	/** add group Ajax
	 * @param model
	 * @param componentsGroup
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(value = "/addGroupHtmlAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public String addGroupHtmlAjax(Model model,
    							@ModelAttribute("ComponentsGroup") ComponentsGroup componentsGroup,
    							@ActiveUser FingraphUser activeUser)
								throws Exception {
		
		int duplicateCnt = componentsService.getComponentsGroupCountByName(componentsGroup);
		if (duplicateCnt > 0) {
			throw new Exception("A name of component group can not be duplicated!");
		}
		
		ComponentsGroup group = componentsService.addComponentsGroup(componentsGroup);
		model.addAttribute("type", "addGroupResult");
		model.addAttribute("group",group);
		return "components/componentsAjaxHtml";
		
	}


	/** edit group name Ajax
	 * @param model
	 * @param componentsGroup
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(value = "/editGroupAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String editGroupAjax(Model model,
    										@ModelAttribute("ComponentsGroup") ComponentsGroup componentsGroup,
    										@ActiveUser FingraphUser activeUser)
    										throws Exception{
		
		int duplicateCnt = componentsService.getComponentsGroupCountByName(componentsGroup);
		if (duplicateCnt > 0) {
			throw new Exception("A name of component group can not be duplicated!");
		}
		
		componentsService.editComponentsGroup(componentsGroup);
		return (new Gson()).toJson(true);
		
	}

	/** remove Group Ajax (Also remove contained components)
	 * @param model
	 * @param componentsGroup
	 * @param activeUser
	 * @return
	 */
	@RequestMapping(value = "/removeGroupAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String removeGroupAjax(Model model,
    											@ModelAttribute("ComponentsGroup") ComponentsGroup componentsGroup,
    											@ActiveUser FingraphUser activeUser) {
		
		componentsService.removeComponentsGroup(componentsGroup);
		return (new Gson()).toJson(true);
		
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
		
		return "include/componentsSection";

	}
	
	@ExceptionHandler (Exception.class)
	@ResponseStatus (value=HttpStatus.NOT_ACCEPTABLE, reason="Invalid Component/Component Group Name")
	public @ResponseBody String handleAllExceptions(Exception ex) {
		Map<String, String> model = new HashMap<String, String>();
        model.put("error", ex.getMessage());
		return (new Gson()).toJson(model);
	}
	
}

