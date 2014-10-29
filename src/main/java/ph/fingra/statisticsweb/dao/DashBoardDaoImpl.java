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

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.AppInfo;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.CurrPrevNumericValue;
import ph.fingra.statisticsweb.domain.CurrPrevTextValue;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;

@Repository
public class DashBoardDaoImpl implements DashBoardDao{
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    // App ====================================================================
    
    @Override
    public App getApp(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getApp", param);
    }
    
    @Override
    public AppInfo getAppInfo(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getAppInfo", param);
    }
    
    // Perfomance Snapshot ====================================================
    
    @Override
    public boolean getIsCompare(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getIsCompare", param);
    }
    
    @Override
    public CurrPrevNumericValue getNewUsers(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getNewUsers", param);
    }
    
    @Override
    public CurrPrevNumericValue getActiveUsers(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getActiveUsers", param);
    }
    
    @Override
    public CurrPrevNumericValue getSessions(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getSessions", param);
    }
    
    @Override
    public CurrPrevNumericValue getSessionLength(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getSessionLength", param);
    }
    
    @Override
    public CurrPrevNumericValue getPageViews(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getPageViews", param);
    }
    
    // Distribution Snapshot ==================================================
    
    @Override
    public String getDayOfWeek(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getDayOfWeek", param);
    }
    
    @Override
    public String getTimeOfDay(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTimeOfDay", param);
    }
    
    @Override
    public List<Map<String, BigDecimal>> getTopCountries(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getTopCountries", param);
    }
    
    @Override
    public CurrPrevTextValue getTopResolution(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTopResolution", param);
    }
    
    @Override
    public CurrPrevTextValue getTopAppVersion(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTopAppVersion", param);
    }
    
    @Override
    public CurrPrevTextValue getTopOsVersion(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTopOsVersion", param);
    }
    
    // Components Snapshot ====================================================
    
    @Override
    public List<ComponentsGroup> getComponentsGroupList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsGroupList", param);
    }
    
    @Override
    public List<Components> getComponentsNewUsersList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsNewUsersList", param);
    }
    
    @Override
    public List<Components> getComponentsActiveUsersList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsActiveUsersList", param);
    }
    
    @Override
    public List<Components> getComponentsPageViewsList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsPageViewsList", param);
    }
    
    @Override
    public List<Components> getComponentsTimeOfDayList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsTimeOfDayList", param);
    }
    
    @Override
    public List<Components> getComponentsTopCountriesList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsTopCountriesList", param);
    }
    
    @Override
    public List<Components> getComponentsInterGroupCommonList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsInterGroupCommonList",param);
    }
    
    @Override
    public List<Components> getComponentsInterGroupTimeOfDayList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsInterGroupTimeOfDayList", param);
    }
    
    @Override
    public List<Components> getComponentsInterGroupTopCountriesList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("dashboard.getComponentsInterGroupTopCountriesList", param);
    }
    
    // Today Snapshot =========================================================
    
    @Override
    public CurrPrevNumericValue getTodayNewUsers(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTodayNewUsers", param);
    }
    
    @Override
    public CurrPrevNumericValue getTodayActiveUsers(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTodayActiveUsers", param);
    }
    
    @Override
    public CurrPrevNumericValue getTodaySessions(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTodaySessions", param);
    }
    
    @Override
    public CurrPrevNumericValue getTodaySessionLength(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTodaySessionLength", param);
    }
    
    @Override
    public CurrPrevNumericValue getTodayPageViews(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectOne("dashboard.getTodayPageViews", param);
    }
}
