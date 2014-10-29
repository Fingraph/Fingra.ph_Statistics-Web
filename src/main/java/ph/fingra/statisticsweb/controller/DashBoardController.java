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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.ComponentsInfo;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.MsDropDown;
import ph.fingra.statisticsweb.security.ActiveUser;
import ph.fingra.statisticsweb.security.FingraphUser;
import ph.fingra.statisticsweb.service.DashBoardService;

import com.google.gson.Gson;


@Controller
@RequestMapping({"/dashboard/*"})
public class DashBoardController extends BaseController {
	
	@Autowired
	private DashBoardService dashBoardService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{appkey}")
	public String dashboard(Model model, 
							@PathVariable("appkey") String appkey,
							@RequestParam(required=false, defaultValue="1w",value="period") String period, 
							DashBoardSearchParam param, 
							@ActiveUser FingraphUser activeUser) {
		
		param.setPeriod(period);
		App app = dashBoardService.getDashBoardAppInfo(param);
		model.addAttribute("app",app);
    
		return "dashboard/dashboard";
		
	}

	
	/** AppList - Ajax
	 * @param searchParam
	 * @return
	 */
	@RequestMapping(value = "/getAppListAjax", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public @ResponseBody String getAppListAjax(@ActiveUser FingraphUser activeUser, HttpServletRequest request) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		DashBoardSearchParam param = new DashBoardSearchParam();
		List<App> appList = dashBoardService.getAppList(param);
		String context = request.getContextPath();
		List<MsDropDown> list = new ArrayList<>();
		if(appList.size()>0){
			for(App app: appList){
				MsDropDown ms = new MsDropDown();
				ms.setValue(app.getAppkey());
				ms.setText(app.getAppname());
				if(app.getPlatform()==1){//ios
					ms.setImage(context+"/resources/img/icon_sel_ios.png");
				}else if(app.getPlatform()==2) {//android
					ms.setImage(context+"/resources/img/icon_sel_android.png");
				}

				list.add(ms);
			}
		}
		paramMap.put("list", list);
		return (new Gson()).toJson(paramMap);
		
	}

	/** COMPONENTS SNAPSHOT
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/dashboard/getComponetsSnapshotAjax", method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
    public @ResponseBody String getComponetsSnapshotAjax(@RequestParam(required=false,defaultValue="1w",value="period") String period,
    													DashBoardSearchParam param) {
		
		param.setPeriod(period);
		ComponentsInfo componentsInfo = dashBoardService.getComponetsSnapshotAjax(param);
		return (new Gson()).toJson(componentsInfo);
		
	}
	
}
