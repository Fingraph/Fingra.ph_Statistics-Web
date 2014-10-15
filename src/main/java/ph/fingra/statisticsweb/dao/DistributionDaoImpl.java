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

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ph.fingra.statisticsweb.domain.DayOfWeekData;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.ResolutionData;
import ph.fingra.statisticsweb.domain.ResolutionTimeSeriesData;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.VersionsData;
import ph.fingra.statisticsweb.domain.VersionsTimeSeriesData;

@Repository
public class DistributionDaoImpl implements DistributionDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<DayOfWeekData> getDayOfWeekList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getDayOfWeekList", searchParam);
	}

	@Override
	public Figures getDayOfWeekFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("distribution.getDayOfWeekFigures", searchParam);
	}

	@Override
	public TimeOfDayData getTimeOfDayList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("distribution.getTimeOfDayList", searchParam);
	}

	@Override
	public Figures getTimeOfDayFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("distribution.getTimeOfDayFigures", searchParam);
	}

	@Override
	public List<String> getOsVersionsDailyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsDailyTopNList", searchParam);
	}

	@Override
	public List<String> getAppVersionsDailyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsDailyTopNList", searchParam);
	}

	@Override
	public List<VersionsData> getOsVersionsDailyTopNSum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsDailyTopNSum", searchParam);
	}

	@Override
	public List<VersionsTimeSeriesData> getOsVersionsDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsDailyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<VersionsData> getAppVersionsDailyTopNSum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsDailyTopNSum", searchParam);
	}

	@Override
	public List<VersionsTimeSeriesData> getAppVersionsDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsDailyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getOsVersionsWeeklyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsWeeklyTopNList", searchParam);
	}

	@Override
	public List<VersionsData> getOsVersionsWeeklyTopNSum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsWeeklyTopNSum", searchParam);
	}

	@Override
	public List<VersionsTimeSeriesData> getOsVersionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsWeeklyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getAppVersionsWeeklyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsWeeklyTopNList", searchParam);
	}

	@Override
	public List<VersionsData> getAppVersionsWeeklyTopNSum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsWeeklyTopNSum", searchParam);
	}

	@Override
	public List<VersionsTimeSeriesData> getAppVersionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsWeeklyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getOsVersionsMonthlyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsMonthlyTopNList", searchParam);
	}

	@Override
	public List<VersionsData> getOsVersionsMonthlyTopNSum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsMonthlyTopNSum", searchParam);
	}

	@Override
	public List<VersionsTimeSeriesData> getOsVersionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getOsVersionsMonthlyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getAppVersionsMonthlyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsMonthlyTopNList", searchParam);
	}

	@Override
	public List<VersionsData> getAppVersionsMonthlyTopNSum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsMonthlyTopNSum", searchParam);
	}

	@Override
	public List<VersionsTimeSeriesData> getAppVersionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getAppVersionsMonthlyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getResolutionDailyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionDailyTopNList", searchParam);
	}

	@Override
	public List<ResolutionData> getResolutionDailySum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionDailySum", searchParam);
	}

	@Override
	public List<ResolutionTimeSeriesData> getResolutionDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionDailyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getResolutionWeeklyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionWeeklyTopNList", searchParam);
	}

	@Override
	public List<ResolutionData> getResolutionWeeklySum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionWeeklySum", searchParam);
	}

	@Override
	public List<ResolutionTimeSeriesData> getResolutionWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionWeeklyTimeSeriesDataList", searchParam);
	}

	@Override
	public List<String> getResolutionMonthlyTopNList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionMonthlyTopNList", searchParam);
	}

	@Override
	public List<ResolutionData> getResolutionMonthlySum(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionMonthlySum", searchParam);
	}

	@Override
	public List<ResolutionTimeSeriesData> getResolutionMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("distribution.getResolutionMonthlyTimeSeriesDataList", searchParam);
	}

}
