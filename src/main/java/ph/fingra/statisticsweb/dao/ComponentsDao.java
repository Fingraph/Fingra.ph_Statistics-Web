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
//import ph.fingra.statisticsweb.domain.ComponentsManage;
//import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.TimeOfDayData;

public interface ComponentsDao {

	List<Components> getNewUsersTotalList(FingraphSearchParam searchParam);

	List<TimeOfDayData> getTimeOfDayTotalList(FingraphSearchParam searchParam);

	List<Components> getTimeOfDayActualFigureList(FingraphSearchParam searchParam);

	List<HashMap> getNewUsersDailyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getNewUsersWeeklyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getNewUsersMonthlyTimeSeriesList(FingraphSearchParam searchParam);

	List<Components> getActiveUsersTotalList(FingraphSearchParam searchParam);

	List<HashMap> getActiveUsersDailyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getActiveUsersWeeklyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getActiveUsersMonthlyTimeSeriesList(FingraphSearchParam searchParam);

	List<Components> getPageViewsTotalList(FingraphSearchParam searchParam);

	List<HashMap> getPageViewsDailyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getPageViewsWeeklyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getPageViewsMonthlyTimeSeriesList(FingraphSearchParam searchParam);

	String getTimeOfDayMaxTime(FingraphSearchParam searchParam);

	List<ActualData> getTimeOfDayActualDataList(FingraphSearchParam searchParam);

	List<HashMap> getTopCountriesDailyTotalTopNList(FingraphSearchParam searchParam);

	BigDecimal getNewUsersAppTotal(FingraphSearchParam searchParam);

	BigDecimal getActiveUsersAppTotal(FingraphSearchParam searchParam);

	BigDecimal getPageViewsAppTotal(FingraphSearchParam searchParam);

	List<ActualData> getTopCountriesActualDataList(FingraphSearchParam searchParam);

//	List<Event> getComponentsList(DashBoardSearchParam param);

	String makeComponentKey();

	void addComponent(Component component);

	void editComponent(Component component);

	void removeComponent(Component component);

	List<HashMap> getNewUsersInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam);

	List<ActualData> getNewUsersActualDataList(FingraphSearchParam searchParam);

	List<HashMap> getNewUsersInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getNewUsersInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam);

	List<ActualData> getActiveUsersActualDataList(FingraphSearchParam searchParam);

	List<HashMap> getActiveUsersInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getActiveUsersInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getActiveUsersInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam);

	List<ActualData> getPageViewsActualDataList(FingraphSearchParam searchParam);

	List<HashMap> getPageViewsInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getPageViewsInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam);

	List<HashMap> getPageViewsInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam);

//	List<ComponentsManage> getComponentsManageList(DashBoardSearchParam param);

	void addComponentsGroup(ComponentsGroup componentsGroup);

	int makeComponentsGroupKey(ComponentsGroup group);

	void updateComponentsGroupName(ComponentsGroup componentsGroup);

	void updateComponentsGroupIsdel(ComponentsGroup componentsGroup);

	void updateComponentsIsdel(ComponentsGroup componentsGroup);

	void addComponentWithGroup(Component component);

	void removeArrayComponent(HashMap map);

	List<ComponentsGroup> getComponentsGroupList(String appkey);

	void moveArrayComponent(HashMap map);

}
