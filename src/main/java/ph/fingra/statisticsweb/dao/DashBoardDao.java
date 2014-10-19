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

package ph.fingra.statisticsweb.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.AppInfo;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.CurrPrevNumericValue;
import ph.fingra.statisticsweb.domain.CurrPrevTextValue;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;

public interface DashBoardDao {
    
	App getApp(DashBoardSearchParam param);
	
	AppInfo getAppInfo(DashBoardSearchParam param);
	
    List<App> getAppListByMemberid(Integer memberid);
    
    boolean getIsCompare(DashBoardSearchParam param);
    
    //PERFORMANCE SNAPSHOT
	CurrPrevNumericValue getNewUsers(DashBoardSearchParam param);
	CurrPrevNumericValue getActiveUsers(DashBoardSearchParam param);
	CurrPrevNumericValue getSessions(DashBoardSearchParam param);
	CurrPrevNumericValue getSessionLength(DashBoardSearchParam param);
	CurrPrevNumericValue getPageViews(DashBoardSearchParam param);
	
    //DISTRIBUTION SNAPSHOT
	String getTimeOfDay(DashBoardSearchParam param);
	List<Map<String, BigDecimal>> getTopContries(DashBoardSearchParam param);
	CurrPrevTextValue getTopResolution(DashBoardSearchParam param);
	CurrPrevTextValue getTopAppVersion(DashBoardSearchParam param);
	CurrPrevTextValue getTopOsVersion(DashBoardSearchParam param);
	String getDayOfWeek(DashBoardSearchParam param);
	
    //COMPONENTS SNAPSHOT
	List<Components> getComponentsNewUsersList(DashBoardSearchParam param);
	List<Components> getComponentsActiveUsersList(DashBoardSearchParam param);
	List<Components> getComponentsPageViewsList(DashBoardSearchParam param);
	List<Components> getComponentsTimeOfDayList(DashBoardSearchParam param);
	List<Components> getComponentsTopCountriesList(DashBoardSearchParam param);
    List<ComponentsGroup> getComponentsGroupList(DashBoardSearchParam param);
    List<Components> getComponentsInterGroupCommonList(DashBoardSearchParam param);
    List<Components> getComponentsInterGroupTimeOfDayList(DashBoardSearchParam param);
    List<Components> getComponentsInterGroupTopCountriesList(DashBoardSearchParam param);
    
    //TODAY SNAPSHOT
    CurrPrevNumericValue getTodayNewUsers(DashBoardSearchParam param);
    CurrPrevNumericValue getTodayActiveUsers(DashBoardSearchParam param);
    CurrPrevNumericValue getTodaySessions(DashBoardSearchParam param);
    CurrPrevNumericValue getTodaySessionLength(DashBoardSearchParam param);
    CurrPrevNumericValue getTodayPageViews(DashBoardSearchParam param);
}
