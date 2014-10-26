package ph.fingra.statisticsweb.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ph.fingra.statisticsweb.dao.ComponentsDao;
import ph.fingra.statisticsweb.dao.CountryDao;
import ph.fingra.statisticsweb.domain.ActualData;
import ph.fingra.statisticsweb.domain.Component;
import ph.fingra.statisticsweb.domain.Components;
import ph.fingra.statisticsweb.domain.ComponentsGroup;
import ph.fingra.statisticsweb.domain.ComponentsManage;
import ph.fingra.statisticsweb.domain.CountryData;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.MsDropDown;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.TopCountriesData;

@Service
public class ComponentsServiceImpl implements ComponentsService {

	@Autowired
	private ComponentsDao componentsDao;

	@Autowired
	private CountryDao countryDao;

	@Override
	public List<Components> getNewUsersTotalList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersTotalList(searchParam);
	}

	@Override
	public List<TimeOfDayData> getTimeOfDayTotalList(FingraphSearchParam searchParam) {
		return componentsDao.getTimeOfDayTotalList(searchParam);
	}

	@Override
	public List<HashMap> getNewUsersDailyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersDailyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getNewUsersWeeklyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersWeeklyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getNewUsersMonthlyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersMonthlyTimeSeriesList(searchParam);
	}

	@Override
	public List<Components> getActiveUsersTotalList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersTotalList(searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersDailyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersDailyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersWeeklyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersWeeklyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersMonthlyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersMonthlyTimeSeriesList(searchParam);
	}

	@Override
	public List<Components> getPageViewsTotalList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsTotalList(searchParam);
	}

	@Override
	public List<HashMap> getPageViewsDailyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsDailyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getPageViewsWeeklyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsWeeklyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getPageViewsMonthlyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsMonthlyTimeSeriesList(searchParam);
	}


	@Override
	public String getTimeOfDayMaxTime(FingraphSearchParam searchParam) {
		return componentsDao.getTimeOfDayMaxTime(searchParam);
	}

	@Override
	public List<ActualData> getTimeOfDayActualDataList(FingraphSearchParam searchParam) {
		List<ActualData> alist = componentsDao.getTimeOfDayActualDataList(searchParam);
		if(alist.size()>0){
			BigDecimal componentTotal = new BigDecimal(0);
			for(ActualData ad: alist){//component 합
				componentTotal = componentTotal.add(ad.getActual());
			}

			for(ActualData ad: alist){
				if(componentTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentAllCom((ad.getActual().divide(componentTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
			}
		}
		return alist;
	}


	@Override
	public TopCountriesData getTopCountriesData(FingraphSearchParam searchParam) {
		TopCountriesData data = new TopCountriesData();

		List<CountryData> sumList = new ArrayList<>();
		if("daily".equals(searchParam.getTerm())){
			sumList = countryDao.getCountrySessionsDailySumList(searchParam);
		}else if("weekly".equals(searchParam.getTerm())){
			sumList = countryDao.getCountrySessionsWeeklySumList(searchParam);
		}else if("monthly".equals(searchParam.getTerm())){
			sumList = countryDao.getCountrySessionsMonthlySumList(searchParam);
		}

		List<MsDropDown> list = new ArrayList<>();
		if(sumList.size()>0){
			List<String> topNList = new ArrayList();

			if(sumList.size()>5){// get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());

					MsDropDown ms = new MsDropDown();// country select box
					ms.setValue(sumList.get(i).getIso2());
					ms.setText(sumList.get(i).getCountry());
					list.add(ms);
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());

					MsDropDown ms = new MsDropDown();// country select box
					ms.setValue(sumList.get(i).getIso2());
					ms.setText(sumList.get(i).getCountry());
					list.add(ms);
				}
			}

			for(int i=0 ; i<topNList.size();i++){
				System.out.println(topNList.get(i));
			}

			searchParam.setTopNList(topNList);// set top5 countries to the search list.
			List<HashMap> tlist = componentsDao.getTopCountriesDailyTotalTopNList(searchParam);
			data.setTlist(tlist);
			data.setSlist(list);

		}
		return data;

	}

	@Override
	public List<ActualData> getTopCountriesActualDataList(FingraphSearchParam searchParam) {
		List<ActualData> alist = componentsDao.getTopCountriesActualDataList(searchParam);
		if(alist.size()>0){
			BigDecimal componentTotal = new BigDecimal(0);
			for(ActualData ad: alist){//Total of components
				componentTotal = componentTotal.add(ad.getActual());
			}

			for(ActualData ad: alist){
				if(componentTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentAllCom((ad.getActual().divide(componentTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
			}
		}
		return alist;
	}

	@Override
	public List<Component> getComponentsList(DashBoardSearchParam param) {
		return componentsDao.getComponentsList(param);
	}

	@Override
	public String addComponent(Component event) {
		componentsDao.addComponent(event);
		return event.getComponentkey();
	}

	@Override
	public void editComponent(Component event) {
		componentsDao.editComponent(event);
	}

	@Override
	public void removeComponent(Component event) {
		componentsDao.removeComponent(event);
	}

	@Override
	public List<HashMap> getNewUsersInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersInterGroupDailyTimeSeriesList(searchParam);
	}

	@Override
	public List<ActualData> getNewUsersActualDataList(FingraphSearchParam searchParam) {
		List<ActualData> alist = componentsDao.getNewUsersActualDataList(searchParam);

		if(alist.size()>0){
			//Total of Apps
			BigDecimal appTotal = componentsDao.getNewUsersAppTotal(searchParam);

			BigDecimal componentTotal = new BigDecimal(0);
			for(ActualData ad: alist){//Total of Components
				componentTotal = componentTotal.add(ad.getActual());
			}
			for(ActualData ad: alist){
				ad.setAppTotal(appTotal);
				if(appTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentTotal((ad.getActual().divide(appTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
				if(componentTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentAllCom((ad.getActual().divide(componentTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
			}
		}
		return alist;
	}

	@Override
	public List<HashMap> getNewUsersInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersInterGroupWeeklyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getNewUsersInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getNewUsersInterGroupMonthlyTimeSeriesList(searchParam);
	}

	@Override
	public List<ActualData> getActiveUsersActualDataList(FingraphSearchParam searchParam) {
		List<ActualData> alist = componentsDao.getActiveUsersActualDataList(searchParam);

		if(alist.size()>0){
			//Total of Apps
			BigDecimal appTotal = componentsDao.getActiveUsersAppTotal(searchParam);

			BigDecimal componentTotal = new BigDecimal(0);
			for(ActualData ad: alist){//Total of Components
				componentTotal = componentTotal.add(ad.getActual());
			}
			for(ActualData ad: alist){
				ad.setAppTotal(appTotal);
				if(appTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentTotal((ad.getActual().divide(appTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
				if(componentTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentAllCom((ad.getActual().divide(componentTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
			}
		}
		return alist;
	}

	@Override
	public List<HashMap> getActiveUsersInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersInterGroupDailyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersInterGroupWeeklyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getActiveUsersInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getActiveUsersInterGroupMonthlyTimeSeriesList(searchParam);
	}

	@Override
	public List<ActualData> getPageViewsActualDataList(FingraphSearchParam searchParam) {
		List<ActualData> alist = componentsDao.getPageViewsActualDataList(searchParam);

		if(alist.size()>0){
			//Total of Apps.
			BigDecimal appTotal = componentsDao.getPageViewsAppTotal(searchParam);

			BigDecimal componentTotal = new BigDecimal(0);
			for(ActualData ad: alist){//Total of components
				componentTotal = componentTotal.add(ad.getActual());
			}
			for(ActualData ad: alist){
				ad.setAppTotal(appTotal);
				if(appTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentTotal((ad.getActual().divide(appTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
				if(componentTotal.compareTo(new BigDecimal(0))==1){
					ad.setPercentAllCom((ad.getActual().divide(componentTotal,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
				}
			}
		}
		return alist;
	}

	@Override
	public List<HashMap> getPageViewsInterGroupDailyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsInterGroupDailyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getPageViewsInterGroupWeeklyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsInterGroupWeeklyTimeSeriesList(searchParam);
	}

	@Override
	public List<HashMap> getPageViewsInterGroupMonthlyTimeSeriesList(FingraphSearchParam searchParam) {
		return componentsDao.getPageViewsInterGroupMonthlyTimeSeriesList(searchParam);
	}

	@Override
	public List<ComponentsManage> getComponentsManageList(DashBoardSearchParam param) {
		List<ComponentsManage> list = componentsDao.getComponentsManageList(param);
		// If component list is null, create default component group.
		if(list.size()==0){
			componentsDao.addComponentsGroup(new ComponentsGroup(param.getAppkey(), 0, "Ungrouped", null, 0));
			list = componentsDao.getComponentsManageList(param);
		}
		return list;
	}

	@Override
	public ComponentsGroup addComponentsGroup(ComponentsGroup group) {
		int groupkey = componentsDao.makeComponentsGroupKey(group);
		if(groupkey >= 0){
			group.setGroupkey(groupkey);
			componentsDao.addComponentsGroup(group);
		}
		return group;
	}

	@Override
	public void editComponentsGroup(ComponentsGroup componentsGroup) {
		componentsDao.updateComponentsGroupName(componentsGroup);
	}

	@Override
	@Transactional
	public void removeComponentsGroup(ComponentsGroup componentsGroup) {
		//updateGroup - isdel=1
		componentsDao.updateComponentsGroupIsdel(componentsGroup);
		//updateEvent
		componentsDao.updateComponentsIsdel(componentsGroup);
	}

	@Override
	public Component addComponentWithGroup(Component event) {
		componentsDao.addComponentWithGroup(event);
		return event;
	}

	@Override
	public void removeArrayComponent(HashMap map) {
		componentsDao.removeArrayComponent(map);
	}

	@Override
	public List<ComponentsGroup> getComponentsGroupList(String appkey) {
		return componentsDao.getComponentsGroupList(appkey);
	}

	@Override
	public void moveArrayComponent(HashMap map) {
		componentsDao.moveArrayComponent(map);
	}

}
