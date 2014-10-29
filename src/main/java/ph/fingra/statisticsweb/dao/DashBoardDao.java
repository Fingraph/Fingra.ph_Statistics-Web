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
    
    // App ====================================================================
    
    public App getApp(DashBoardSearchParam param);
    
    public AppInfo getAppInfo(DashBoardSearchParam param);
    
    // Perfomance Snapshot ====================================================
    
    public boolean getIsCompare(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getNewUsers(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getActiveUsers(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getSessions(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getSessionLength(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getPageViews(DashBoardSearchParam param);
    
    // Distribution Snapshot ==================================================
    
    public String getDayOfWeek(DashBoardSearchParam param);
    
    public String getTimeOfDay(DashBoardSearchParam param);
    
    public List<Map<String, BigDecimal>> getTopCountries(DashBoardSearchParam param);
    
    public CurrPrevTextValue getTopResolution(DashBoardSearchParam param);
    
    public CurrPrevTextValue getTopAppVersion(DashBoardSearchParam param);
    
    public CurrPrevTextValue getTopOsVersion(DashBoardSearchParam param);
    
    // Components Snapshot ====================================================
    
    public List<ComponentsGroup> getComponentsGroupList(DashBoardSearchParam param);
    
    public List<Components> getComponentsNewUsersList(DashBoardSearchParam param);
    
    public List<Components> getComponentsActiveUsersList(DashBoardSearchParam param);
    
    public List<Components> getComponentsPageViewsList(DashBoardSearchParam param);
    
    public List<Components> getComponentsTimeOfDayList(DashBoardSearchParam param);
    
    public List<Components> getComponentsTopCountriesList(DashBoardSearchParam param);
    
    public List<Components> getComponentsInterGroupCommonList(DashBoardSearchParam param);
    
    public List<Components> getComponentsInterGroupTimeOfDayList(DashBoardSearchParam param);
    
    public List<Components> getComponentsInterGroupTopCountriesList(DashBoardSearchParam param);
    
    // Today Snapshot =========================================================
    
    public CurrPrevNumericValue getTodayNewUsers(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getTodayActiveUsers(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getTodaySessions(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getTodaySessionLength(DashBoardSearchParam param);
    
    public CurrPrevNumericValue getTodayPageViews(DashBoardSearchParam param);
}
