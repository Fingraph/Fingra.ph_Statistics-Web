package ph.fingra.statisticsweb.service;

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
import ph.fingra.statisticsweb.domain.TopCountriesData;

public interface ComponentsService {

	List<Components> getNewUsersTotalList(FingraphSearchParam searchParam);

	List<TimeOfDayData> getTimeOfDayTotalList(FingraphSearchParam searchParam);

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

	TopCountriesData getTopCountriesData(FingraphSearchParam searchParam);

	List<ActualData> getTopCountriesActualDataList(FingraphSearchParam searchParam);

	List<Component> getComponentsList(DashBoardSearchParam param);

	int getComponentCountByName(Component event);
	
	String addComponent(Component event);

	void editComponent(Component event);

	void removeComponent(Component event);

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

	List<ComponentsManage> getComponentsManageList(DashBoardSearchParam param);

	int getComponentsGroupCountByName(ComponentsGroup group);
	
	ComponentsGroup addComponentsGroup(ComponentsGroup group);

	void editComponentsGroup(ComponentsGroup componentsGroup);

	void removeComponentsGroup(ComponentsGroup componentsGroup);

	Component addComponentWithGroup(Component event);

	void removeArrayComponent(HashMap map);

	List<ComponentsGroup> getComponentsGroupList(String appkey);

	void moveArrayComponent(HashMap map);

}

