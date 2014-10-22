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
import java.util.HashMap;
import java.util.List;

import ph.fingra.statisticsweb.domain.ActualData;
import ph.fingra.statisticsweb.domain.Component;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.ComponentsManage;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.TimeOfDayData;

@SuppressWarnings("rawtypes")
public interface ComponentsDao {
    
    // Manage Components ======================================================
    
    public List<ComponentsManage> getComponentsManageList(DashBoardSearchParam param);
    
    public List<Component> getComponentsList(DashBoardSearchParam param);
    
    public List<ComponentsGroup> getComponentsGroupList(String appkey);
    
    public void addComponent(Component component);
    
    public void addComponentWithGroup(Component component);
    
    public void editComponent(Component component);
    
    public void removeComponent(Component component);
    
    public void addComponentsGroup(ComponentsGroup componentsGroup);
    
    public int makeComponentsGroupKey(ComponentsGroup group);
    
    public void updateComponentsGroupName(ComponentsGroup componentsGroup);
    
    public void updateComponentsGroupIsdel(ComponentsGroup componentsGroup);
    
    public void updateComponentsIsdel(ComponentsGroup componentsGroup);
    
    public void removeArrayComponent(HashMap map);
    
    public void moveArrayComponent(HashMap map);
    
    // New Users ==============================================================
    
    public List<Components> getNewUsersTotalList(FingraphSearchParam searchParam);
    
    public List<HashMap> getNewUsersDailyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getNewUsersInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getNewUsersWeeklyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getNewUsersInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getNewUsersMonthlyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getNewUsersInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam);
    
    public BigDecimal getNewUsersAppTotal(FingraphSearchParam searchParam);
    
    public List<ActualData> getNewUsersActualDataList(FingraphSearchParam searchParam);
    
    // Active Users ===========================================================
    
    public List<Components> getActiveUsersTotalList(FingraphSearchParam searchParam);
    
    public List<HashMap> getActiveUsersDailyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getActiveUsersInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getActiveUsersWeeklyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getActiveUsersInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getActiveUsersMonthlyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getActiveUsersInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam);
    
    public BigDecimal getActiveUsersAppTotal(FingraphSearchParam searchParam);
    
    public List<ActualData> getActiveUsersActualDataList(FingraphSearchParam searchParam);
    
    // Page Views =============================================================
    
    public List<Components> getPageViewsTotalList(FingraphSearchParam searchParam);
    
    public List<HashMap> getPageViewsDailyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getPageViewsInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getPageViewsWeeklyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getPageViewsInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getPageViewsMonthlyTimeSeriesList(FingraphSearchParam searchParam);
    
    public List<HashMap> getPageViewsInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam);
    
    public BigDecimal getPageViewsAppTotal(FingraphSearchParam searchParam);
    
    public List<ActualData> getPageViewsActualDataList(FingraphSearchParam searchParam);
    
    // Time Of Day ============================================================
    
    public List<TimeOfDayData> getTimeOfDayTotalList(FingraphSearchParam searchParam);
    
    public String getTimeOfDayMaxTime(FingraphSearchParam searchParam);
    
    public List<ActualData> getTimeOfDayActualDataList(FingraphSearchParam searchParam);
    
    // Top Countries ==========================================================
    
    public List<HashMap> getTopCountriesDailyTotalTopNList(FingraphSearchParam searchParam);
    
    public List<ActualData> getTopCountriesActualDataList(FingraphSearchParam searchParam);
}
