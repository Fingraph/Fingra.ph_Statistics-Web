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

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ph.fingra.statisticsweb.domain.ActualData;
import ph.fingra.statisticsweb.domain.Component;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.ComponentsManage;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.TimeOfDayData;

@SuppressWarnings("rawtypes")
@Repository
public class ComponentsDaoImpl implements ComponentsDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// Manage Components ======================================================

	@Override
	public List<ComponentsManage> getComponentsManageList(
			DashBoardSearchParam param) {
		return sqlSessionTemplate.selectList(
				"components.getComponentsManageList", param);
	}

	@Override
	public List<Component> getComponentsList(DashBoardSearchParam param) {
		return sqlSessionTemplate.selectList("components.getComponentsList",
				param);
	}

	@Override
	public List<ComponentsGroup> getComponentsGroupList(String appkey) {
		return sqlSessionTemplate.selectList(
				"components.getComponentsGroupList", appkey);
	}

	@Override
	public int getComponentCountByName(Component component) {
		return sqlSessionTemplate.selectOne(
				"components.getComponentCountByName", component);
	}
	
	@Override
	public void addComponent(Component component) {
		sqlSessionTemplate.insert("components.addComponent", component);
	}
	
	public int countByEmail(String email) {
        return sqlSessionTemplate.selectOne("member.countByEmail", email);
    }

	@Override
	public void addComponentWithGroup(Component component) {
		sqlSessionTemplate
				.insert("components.addComponentWithGroup", component);
	}

	@Override
	public void editComponent(Component component) {
		sqlSessionTemplate.update("components.editComponent", component);
	}

	@Override
	public void removeComponent(Component component) {
		sqlSessionTemplate.update("components.removeComponent", component);
	}
	
	@Override
	public int getComponentsGroupCountByName(ComponentsGroup componentsGroup) {
		return sqlSessionTemplate.selectOne(
				"components.getComponentsGroupCountByName", componentsGroup);
	};
	
	@Override
	public void addComponentsGroup(ComponentsGroup componentsGroup) {
		sqlSessionTemplate.insert("components.addComponentsGroup",
				componentsGroup);
	}

	@Override
	public int makeComponentsGroupKey(ComponentsGroup group) {
		return sqlSessionTemplate.selectOne(
				"components.makeComponentsGroupKey", group);
	}

	@Override
	public void updateComponentsGroupName(ComponentsGroup componentsGroup) {
		sqlSessionTemplate.update("components.updateComponentsGroupName",
				componentsGroup);
	}

	@Override
	public void updateComponentsGroupIsdel(ComponentsGroup componentsGroup) {
		sqlSessionTemplate.update("components.updateComponentsGroupIsdel",
				componentsGroup);
	}

	@Override
	public void updateComponentsIsdel(ComponentsGroup componentsGroup) {
		sqlSessionTemplate.update("components.updateComponentsIsdel",
				componentsGroup);
	}

	@Override
	public void removeArrayComponent(HashMap map) {
		sqlSessionTemplate.update("components.removeArrayComponent", map);
	}

	@Override
	public void moveArrayComponent(HashMap map) {
		sqlSessionTemplate.update("components.moveArrayComponent", map);
	}

	// New Users ==============================================================

	@Override
	public List<Components> getNewUsersTotalList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("components.getNewUsersTotalList",
				searchParam);
	}

	@Override
	public List<HashMap> getNewUsersDailyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersDailyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getNewUsersInterGroupDailyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersInterGroupDailyTimeSeriesList",
				searchParam);
	}

	@Override
	public List<HashMap> getNewUsersWeeklyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersWeeklyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getNewUsersInterGroupWeeklyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersInterGroupWeeklyTimeSeriesList",
				searchParam);
	}

	@Override
	public List<HashMap> getNewUsersMonthlyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersMonthlyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getNewUsersInterGroupMonthlyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersInterGroupMonthlyTimeSeriesList",
				searchParam);
	}

	@Override
	public BigDecimal getNewUsersAppTotal(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("components.getNewUsersAppTotal",
				searchParam);
	}

	@Override
	public List<ActualData> getNewUsersActualDataList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getNewUsersActualDataList", searchParam);
	}

	// Active Users ===========================================================

	@Override
	public List<Components> getActiveUsersTotalList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersTotalList", searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersDailyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersDailyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersInterGroupDailyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersInterGroupDailyTimeSeriesList",
				searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersWeeklyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersWeeklyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersInterGroupWeeklyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersInterGroupWeeklyTimeSeriesList",
				searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersMonthlyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersMonthlyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersInterGroupMonthlyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersInterGroupMonthlyTimeSeriesList",
				searchParam);
	}

	@Override
	public BigDecimal getActiveUsersAppTotal(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne(
				"components.getActiveUsersAppTotal", searchParam);
	}

	@Override
	public List<ActualData> getActiveUsersActualDataList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getActiveUsersActualDataList", searchParam);
	}

	// Page Views =============================================================

	@Override
	public List<Components> getPageViewsTotalList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsTotalList", searchParam);
	}

	@Override
	public List<HashMap> getPageViewsDailyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsDailyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getPageViewsInterGroupDailyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsInterGroupDailyTimeSeriesList",
				searchParam);
	}

	@Override
	public List<HashMap> getPageViewsWeeklyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsWeeklyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getPageViewsInterGroupWeeklyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsInterGroupWeeklyTimeSeriesList",
				searchParam);
	}

	@Override
	public List<HashMap> getPageViewsMonthlyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsMonthlyTimeSeriesList", searchParam);
	}

	@Override
	public List<HashMap> getPageViewsInterGroupMonthlyTimeSeriesList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsInterGroupMonthlyTimeSeriesList",
				searchParam);
	}

	@Override
	public BigDecimal getPageViewsAppTotal(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("components.getPageViewsAppTotal",
				searchParam);
	}

	@Override
	public List<ActualData> getPageViewsActualDataList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getPageViewsActualDataList", searchParam);
	}

	// Time Of Day ============================================================

	@Override
	public List<TimeOfDayData> getTimeOfDayTotalList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getTimeOfDayTotalList", searchParam);
	}

	@Override
	public String getTimeOfDayMaxTime(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("components.getTimeOfDayMaxTime",
				searchParam);
	}

	@Override
	public List<ActualData> getTimeOfDayActualDataList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getTimeOfDayActualDataList", searchParam);
	}

	// Top Countries ==========================================================

	@Override
	public List<HashMap> getTopCountriesDailyTotalTopNList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getTopCountriesDailyTotalTopNList", searchParam);
	}

	@Override
	public List<ActualData> getTopCountriesActualDataList(
			FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList(
				"components.getTopCountriesActualDataList", searchParam);
	}
}
