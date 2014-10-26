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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.fingra.statisticsweb.dao.DistributionDao;
import ph.fingra.statisticsweb.domain.DayOfWeekData;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.ResolutionData;
import ph.fingra.statisticsweb.domain.ResolutionTimeSeriesData;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.TopResolutionData;
import ph.fingra.statisticsweb.domain.TopVersionsData;
import ph.fingra.statisticsweb.domain.VersionsData;
import ph.fingra.statisticsweb.domain.VersionsTimeSeriesData;

@Service
public class DistributionServiceImpl implements DistributionService {
	
	@Autowired
	private DistributionDao distributionDao;

	@Override
	public List<DayOfWeekData> getDayOfWeekList(FingraphSearchParam searchParam) {
		return distributionDao.getDayOfWeekList(searchParam);
	}

	@Override
	public Figures getDayOfWeekFigures(FingraphSearchParam searchParam) {
		Figures figures = distributionDao.getDayOfWeekFigures(searchParam);
		if(figures == null){
			figures = new Figures();
			figures.setMost("N/A");
			figures.setLeast("N/A");
		}
		return figures;
	}

	@Override
	public TimeOfDayData getTimeOfDayList(FingraphSearchParam searchParam) {
		TimeOfDayData total = distributionDao.getTimeOfDayList(searchParam);
		if(total !=null) total.calculateTotal();
		return total;
	}

	@Override
	public Figures getTimeOfDayFigures(FingraphSearchParam searchParam) {
		return distributionDao.getTimeOfDayFigures(searchParam);
	}

	@Override
	public TopVersionsData getOsVesionsDailyData(FingraphSearchParam searchParam) {
		TopVersionsData data = new TopVersionsData();

		List<String> osTopNList = distributionDao.getOsVersionsDailyTopNList(searchParam);
		if(osTopNList.size()>0){
			searchParam.setTopNList(osTopNList);
			data.setTopOsVer(osTopNList.get(0));
			List<VersionsData> sumList = distributionDao.getOsVersionsDailyTopNSum(searchParam);
			data.setSumList(sumList);

			List<VersionsTimeSeriesData> vtsList = distributionDao.getOsVersionsDailyTimeSeriesDataList(searchParam);
			data.setVtsList(vtsList);
		}
		return data;
	}

	@Override
	public TopVersionsData getAppVesionsDailyData(FingraphSearchParam searchParam) {
		TopVersionsData data = new TopVersionsData();

		List<String> appTopNList = distributionDao.getAppVersionsDailyTopNList(searchParam);
		if(appTopNList.size()>0){
			searchParam.setTopNList(appTopNList);
			data.setTopAppVer(appTopNList.get(0));
			List<VersionsData> sumList = distributionDao.getAppVersionsDailyTopNSum(searchParam);
			data.setSumList(sumList);

			List<VersionsTimeSeriesData> vtsList = distributionDao.getAppVersionsDailyTimeSeriesDataList(searchParam);
			data.setVtsList(vtsList);
		}

		return data;
	}

	@Override
	public TopVersionsData getOsVesionsWeeklyData(FingraphSearchParam searchParam) {
		TopVersionsData data = new TopVersionsData();

		List<String> osTopNList = distributionDao.getOsVersionsWeeklyTopNList(searchParam);
		if(osTopNList.size()>0){
			searchParam.setTopNList(osTopNList);
			data.setTopOsVer(osTopNList.get(0));
			List<VersionsData> sumList = distributionDao.getOsVersionsWeeklyTopNSum(searchParam);
			data.setSumList(sumList);

			List<VersionsTimeSeriesData> vtsList = distributionDao.getOsVersionsWeeklyTimeSeriesDataList(searchParam);
			data.setVtsList(vtsList);
		}
		return data;
	}

	@Override
	public TopVersionsData getAppVesionsWeeklyData(FingraphSearchParam searchParam) {
		TopVersionsData data = new TopVersionsData();

		List<String> appTopNList = distributionDao.getAppVersionsWeeklyTopNList(searchParam);
		if(appTopNList.size()>0){
			searchParam.setTopNList(appTopNList);
			data.setTopAppVer(appTopNList.get(0));
			List<VersionsData> sumList = distributionDao.getAppVersionsWeeklyTopNSum(searchParam);
			data.setSumList(sumList);

			List<VersionsTimeSeriesData> vtsList = distributionDao.getAppVersionsWeeklyTimeSeriesDataList(searchParam);
			data.setVtsList(vtsList);
		}

		return data;
	}

	@Override
	public TopVersionsData getOsVesionsMonthlyData(FingraphSearchParam searchParam) {
		TopVersionsData data = new TopVersionsData();

		List<String> osTopNList = distributionDao.getOsVersionsMonthlyTopNList(searchParam);
		if(osTopNList.size()>0){
			searchParam.setTopNList(osTopNList);
			data.setTopOsVer(osTopNList.get(0));
			List<VersionsData> sumList = distributionDao.getOsVersionsMonthlyTopNSum(searchParam);
			data.setSumList(sumList);

			List<VersionsTimeSeriesData> vtsList = distributionDao.getOsVersionsMonthlyTimeSeriesDataList(searchParam);
			data.setVtsList(vtsList);
		}
		return data;
	}

	@Override
	public TopVersionsData getAppVesionsMonthlyData(FingraphSearchParam searchParam) {
		TopVersionsData data = new TopVersionsData();

		List<String> appTopNList = distributionDao.getAppVersionsMonthlyTopNList(searchParam);
		if(appTopNList.size()>0){
			searchParam.setTopNList(appTopNList);
			data.setTopAppVer(appTopNList.get(0));
			List<VersionsData> sumList = distributionDao.getAppVersionsMonthlyTopNSum(searchParam);
			data.setSumList(sumList);

			List<VersionsTimeSeriesData> vtsList = distributionDao.getAppVersionsMonthlyTimeSeriesDataList(searchParam);
			data.setVtsList(vtsList);
		}

		return data;
	}

	@Override
	public TopResolutionData getResolutionDailyData(FingraphSearchParam searchParam) {
		TopResolutionData data = new TopResolutionData();

		List<String> rTopNList = distributionDao.getResolutionDailyTopNList(searchParam);
		if(rTopNList.size()>0){

			if(rTopNList.size() > 5){
				searchParam.setTopNList(rTopNList.subList(0, searchParam.getTopN()));
				searchParam.setOthers(true);
			}else{
				searchParam.setTopNList(rTopNList);
				searchParam.setOthers(false);
			}

			List<ResolutionData> sumList = distributionDao.getResolutionDailySum(searchParam);
			data.setSumList(sumList);
			List<ResolutionTimeSeriesData> rtsList = distributionDao.getResolutionDailyTimeSeriesDataList(searchParam);
			data.setRtsList(rtsList);
		}

		return data;
	}

	@Override
	public TopResolutionData getResolutionWeeklyData(FingraphSearchParam searchParam) {
		TopResolutionData data = new TopResolutionData();

		List<String> rTopNList = distributionDao.getResolutionWeeklyTopNList(searchParam);
		if(rTopNList.size()>0){

			if(rTopNList.size() > 5){
				searchParam.setTopNList(rTopNList.subList(0, searchParam.getTopN()));
				searchParam.setOthers(true);
			}else{
				searchParam.setTopNList(rTopNList);
				searchParam.setOthers(false);
			}

			List<ResolutionData> sumList = distributionDao.getResolutionWeeklySum(searchParam);
			data.setSumList(sumList);
			List<ResolutionTimeSeriesData> rtsList = distributionDao.getResolutionWeeklyTimeSeriesDataList(searchParam);
			data.setRtsList(rtsList);
		}

		return data;
	}

	@Override
	public TopResolutionData getResolutionMonthlyData(FingraphSearchParam searchParam) {
		TopResolutionData data = new TopResolutionData();

		List<String> rTopNList = distributionDao.getResolutionMonthlyTopNList(searchParam);
		if(rTopNList.size()>0){

			if(rTopNList.size() > 5){
				searchParam.setTopNList(rTopNList.subList(0, searchParam.getTopN()));
				searchParam.setOthers(true);
			}else{
				searchParam.setTopNList(rTopNList);
				searchParam.setOthers(false);
			}

			List<ResolutionData> sumList = distributionDao.getResolutionMonthlySum(searchParam);
			data.setSumList(sumList);
			List<ResolutionTimeSeriesData> rtsList = distributionDao.getResolutionMonthlyTimeSeriesDataList(searchParam);
			data.setRtsList(rtsList);
		}

		return data;
	}

}
