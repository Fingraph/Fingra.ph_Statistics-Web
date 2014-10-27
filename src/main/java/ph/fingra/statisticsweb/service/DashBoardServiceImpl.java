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

package ph.fingra.statisticsweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import ph.fingra.statisticsweb.common.AppPlatform;
import ph.fingra.statisticsweb.dao.AppDao;
import ph.fingra.statisticsweb.dao.DashBoardDao;
import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.AppInfo;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.ComponentsInfo;
import ph.fingra.statisticsweb.domain.DashBoard;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.exception.UnauthorizedAccessException;

@Service
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired
	private DashBoardDao dashBoardDao;
	
	@Autowired
	private AppDao appDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public App getApp(DashBoardSearchParam param) {
		
		App app = dashBoardDao.getApp(param);
		if (app == null) {
			throw new UnauthorizedAccessException("UnauthorizedAcessException");
		}
		AppInfo appInfo = dashBoardDao.getAppInfo(param);
		app.setAppInfo(appInfo);
		checkAppIcon(app);
		app.setDashBoard(new DashBoard());
		
		return app;
	}

	@Override
	public App getDashBoardAppInfo(DashBoardSearchParam param) {
		
		App app = getApp(param);
		DashBoard dashBoard = app.getDashBoard();
		
		//NOTIFICATIONS
		//dashBoard.setInData(dashBoardDao.getIsData(param)>0?true:false);
		//dashBoard.setLogs(dashBoardDao.getLogs(param));
		
		//TODAY SNAPSHOT
		dashBoard.setTodayNewUsers(dashBoardDao.getTodayNewUsers(param));
		dashBoard.setTodayActiveUsers(dashBoardDao.getTodayActiveUsers(param));
		dashBoard.setTodaySessions(dashBoardDao.getTodaySessions(param));
		dashBoard.setTodaySessionLength(dashBoardDao.getTodaySessionLength(param));
		dashBoard.setTodayPageViews(dashBoardDao.getTodayPageViews(param));
		
		//TODAY SNAPSHOT - TIME INFORMATION
		DateTime now = DateTime.now();
		String nowTime = "";
		String prevTime = "";
		// Show statistics after 10 minutes.
		if( now.getMinuteOfHour() < 10){ // Before 10 minutes 
			String nowTemp = now.equals("0") ? "23" : String.valueOf(now.getHourOfDay()-1);
			nowTime = (nowTemp.length() < 2) ? nowTemp = "0"+nowTemp : nowTemp;
			String prevTemp = nowTime.equals("00") ? "23" : String.valueOf(Integer.parseInt(nowTime) - 1);
			prevTime = (prevTemp.length() < 2) ? prevTemp = "0"+prevTemp : prevTemp;
		} else { // After 10 minutes
			nowTime = (String.valueOf(now.getHourOfDay()).length() < 2) ? "0"+String.valueOf(now.getHourOfDay()) : String.valueOf(now.getHourOfDay());
			String prevTemp = now.equals("0") ? "23" : String.valueOf(now.getHourOfDay()-1);
			prevTime = (prevTemp.length() < 2) ? prevTemp = "0"+prevTemp : prevTemp;
		}
		dashBoard.setTodayNowTime(nowTime);
		dashBoard.setTodayPrevTime(prevTime);
		
		//PERFORMANCE SNAPSHOT
		dashBoard.setCompare(dashBoardDao.getIsCompare(param));
		dashBoard.setNewUsers(dashBoardDao.getNewUsers(param));
		dashBoard.setActiveUsers(dashBoardDao.getActiveUsers(param));
		dashBoard.setSessions(dashBoardDao.getSessions(param));
		dashBoard.setSessionLength(dashBoardDao.getSessionLength(param));
		dashBoard.setPageViews(dashBoardDao.getPageViews(param));
		//PERFORMANCE SNAPSHOT - period
		dashBoard.setThisWeek(param.getFrom() +" ~ "+ param.getTo());
		
		//DISTRIBUTION SNAPSHOT
		dashBoard.setDayOfWeek(dashBoardDao.getDayOfWeek(param));
		dashBoard.setTimeOfDay(dashBoardDao.getTimeOfDay(param));
		dashBoard.setTopContries(dashBoardDao.getTopContries(param));
		dashBoard.setTopResolution(dashBoardDao.getTopResolution(param));
		dashBoard.setTopAppVersion(dashBoardDao.getTopAppVersion(param));
		dashBoard.setTopOsVersion(dashBoardDao.getTopOsVersion(param));
		
		//COMPONENTS Group List
		List<ComponentsGroup> componentGrpList =  dashBoardDao.getComponentsGroupList(param);
		dashBoard.setComponentGrpList(componentGrpList);
		
		app.setDashBoard(dashBoard);
		return app;
	}

	@Override
	public App getPerformanceSectionInfo(DashBoardSearchParam param) {

		App app = getApp(param);
		DashBoard dashBoard = app.getDashBoard();

		//PERFORMANCE SNAPSHOT
		dashBoard.setCompare(dashBoardDao.getIsCompare(param));
		dashBoard.setNewUsers(dashBoardDao.getNewUsers(param));
		dashBoard.setActiveUsers(dashBoardDao.getActiveUsers(param));
		dashBoard.setSessions(dashBoardDao.getSessions(param));
		dashBoard.setSessionLength(dashBoardDao.getSessionLength(param));
		dashBoard.setPageViews(dashBoardDao.getPageViews(param));
		
		//PERFORMANCE SNAPSHOT thisWeek
		dashBoard.setThisWeek(param.getFrom() +" ~ "+ param.getTo());
		app.setDashBoard(dashBoard);
		
		return app;
		
	}

	@Override
	public App getDistributionSectionInfo(DashBoardSearchParam param) {

		App app = getApp(param);

		DashBoard dashBoard = app.getDashBoard();

		//DISTRIBUTION SNAPSHOT
		dashBoard.setDayOfWeek(dashBoardDao.getDayOfWeek(param));
		dashBoard.setTimeOfDay(dashBoardDao.getTimeOfDay(param));
		dashBoard.setTopContries(dashBoardDao.getTopContries(param));
		dashBoard.setTopResolution(dashBoardDao.getTopResolution(param));
		dashBoard.setTopAppVersion(dashBoardDao.getTopAppVersion(param));
		dashBoard.setTopOsVersion(dashBoardDao.getTopOsVersion(param));
		
		//DISTRIBUTION SNAPSHOT thisWeek
		dashBoard.setThisWeek(param.getFrom() +" ~ "+ param.getTo());
		app.setDashBoard(dashBoard);
		
		return app;
		
	}

	@Override
	public App getComponentsSectionInfo(DashBoardSearchParam param) {

		App app = getApp(param);

		DashBoard dashBoard = app.getDashBoard();
		List<ComponentsGroup> componentGrpList =  dashBoardDao.getComponentsGroupList(param);
		dashBoard.setComponentGrpList(componentGrpList);
		dashBoard.setThisWeek(param.getFrom() +" ~ "+ param.getTo());
		app.setDashBoard(dashBoard);

    	return app;

	}
	

	@Override
	public List<App> getAppList(DashBoardSearchParam param) {
	
		return appDao.getAppList(param);
		
	}

	@Override
	public ComponentsInfo getComponetsSnapshotAjax(DashBoardSearchParam param) {
		ComponentsInfo componentsInfo = new ComponentsInfo();
		
		if(param.getGroupkey() ==-1){//INTRGROUP
			param.setMenu("newUsers");	componentsInfo.setNewUsersList(dashBoardDao.getComponentsInterGroupCommonList(param));
			param.setMenu("activeUsers");	componentsInfo.setActiveUsersList(dashBoardDao.getComponentsInterGroupCommonList(param));
			param.setMenu("pageViews");
			List<Components> pageViewList = dashBoardDao.getComponentsInterGroupCommonList(param);
			componentsInfo.setPageViewsList(pageViewList);

			if(pageViewList.size()>0){
				List<Integer> topNGrpList = new ArrayList<Integer>();
				for(int i=0; i<pageViewList.size();i++){
					topNGrpList.add(pageViewList.get(i).getGroupkey());
				}
				param.setTopNGrpList(topNGrpList);

				componentsInfo.setTimeOfDayList(dashBoardDao.getComponentsInterGroupTimeOfDayList(param));
				componentsInfo.setTopCountriesList(dashBoardDao.getComponentsInterGroupTopCountriesList(param));
			}

		}else{//HOT COMPONENTS:-1  GROUP-COMPONENTS: gt 0
			componentsInfo.setNewUsersList(dashBoardDao.getComponentsNewUsersList(param));
			componentsInfo.setActiveUsersList(dashBoardDao.getComponentsActiveUsersList(param));

			List<Components> pageViewList = dashBoardDao.getComponentsPageViewsList(param);
			componentsInfo.setPageViewsList(pageViewList);
			if(pageViewList.size()>0){
				List<String> topNList = new ArrayList<String>();
				for(int i=0; i<pageViewList.size();i++){
					topNList.add(pageViewList.get(i).getComponentkey());
				}
				param.setTopNList(topNList);

				componentsInfo.setTimeOfDayList(dashBoardDao.getComponentsTimeOfDayList(param));
				componentsInfo.setTopCountriesList(dashBoardDao.getComponentsTopCountriesList(param));
			}

		}
		
		return componentsInfo;
		
	}

	@Override
	public App getTodaySectionInfo(DashBoardSearchParam param) {

		App app = getApp(param);

		DashBoard dashBoard = app.getDashBoard();

		//TODAY SNAPSHOT
		dashBoard.setTodayNewUsers(dashBoardDao.getTodayNewUsers(param));
		dashBoard.setTodayActiveUsers(dashBoardDao.getTodayActiveUsers(param));
		dashBoard.setTodaySessions(dashBoardDao.getTodaySessions(param));
		dashBoard.setTodaySessionLength(dashBoardDao.getTodaySessionLength(param));
		dashBoard.setTodayPageViews(dashBoardDao.getTodayPageViews(param));
		
		//Time of TODAY SNAPSHOT
		DateTime now = DateTime.now();
		String nowTime = "";
		String prevTime = "";
		// Show statistics after 10 minutes
		if( now.getMinuteOfHour() < 10){ // Before 10 minutes 
			String nowTemp = now.equals("0") ? "23" : String.valueOf(now.getHourOfDay()-1);
			nowTime = (nowTemp.length() < 2) ? nowTemp = "0"+nowTemp : nowTemp;
			String prevTemp = nowTime.equals("00") ? "23" : String.valueOf(Integer.parseInt(nowTime) - 1);
			prevTime = (prevTemp.length() < 2) ? prevTemp = "0"+prevTemp : prevTemp;
		}else{ // After 10 minutes
			nowTime = (String.valueOf(now.getHourOfDay()).length() < 2) ? "0"+String.valueOf(now.getHourOfDay()) : String.valueOf(now.getHourOfDay());
			String prevTemp = now.equals("0") ? "23" : String.valueOf(now.getHourOfDay()-1);
			prevTime = (prevTemp.length() < 2) ? prevTemp = "0"+prevTemp : prevTemp;
		}
		dashBoard.setTodayNowTime(nowTime);
		dashBoard.setTodayPrevTime(prevTime);
		dashBoard.setToday(now.toString("yyyy-MM-dd"));
		dashBoard.setYesterday(now.minusDays(1).toString("yyyy-MM-dd"));
		app.setDashBoard(dashBoard);
		
		return app;
		
	}
	
	private void checkAppIcon(App app)  {
		if ((app.getAppInfo() != null && app.getAppInfo().getSmallicon() != null) || !app.hasValidAppId())
		    return;
		if (app.getAppInfo() == null) {
		    AppInfo appInfo = new AppInfo();
		    appInfo.setAppkey(app.getAppkey());
		    app.setAppInfo(appInfo);
		}
		if (AppPlatform.valueOf(app.getPlatform()) == AppPlatform.IPHONE) {
		    ResponseEntity<String> response = restTemplate.getForEntity("https://itunes.apple.com/lookup?id={appId}", String.class, app.getAppid());
		    if (response.getStatusCode() != HttpStatus.OK)
			return;
		    JsonObject result = (JsonObject) new JsonParser().parse(response.getBody());
		    JsonArray arr = result.getAsJsonArray("results");
		    if (result.getAsJsonPrimitive("resultCount").getAsInt() != 1)
			return;
		    JsonPrimitive smallIconUrl = arr.get(0).getAsJsonObject().getAsJsonPrimitive("artworkUrl60");
		    app.getAppInfo().setSmallicon(smallIconUrl.getAsString());
		} else {
		    Document d = null;
		    try {
			d = Jsoup.connect("https://play.google.com/store/apps/details?id=" + app.getAppid()).get();
			Elements div = d.getElementsByAttributeValueContaining("class", "cover-container");
			//System.out.println(div.hasClass("cover-container"));
			if(div.size()==0) return;
			String path = div.get(0).getElementsByTag("img").attr("src");
			app.getAppInfo().setSmallicon(path);
		    } catch (IOException e) {
			e.printStackTrace();
			return;
		    }
		}
		appDao.updateAppInfo(app.getAppInfo());

	    }

}
