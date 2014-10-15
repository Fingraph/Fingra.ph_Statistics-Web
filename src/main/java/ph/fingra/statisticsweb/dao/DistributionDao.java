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

import ph.fingra.statisticsweb.domain.DayOfWeekData;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.ResolutionTimeSeriesData;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.VersionsData;
import ph.fingra.statisticsweb.domain.VersionsTimeSeriesData;
import ph.fingra.statisticsweb.domain.ResolutionData;

public interface DistributionDao {

	List<DayOfWeekData> getDayOfWeekList(FingraphSearchParam searchParam);

	TimeOfDayData getTimeOfDayList(FingraphSearchParam searchParam);

	Figures getTimeOfDayFigures(FingraphSearchParam searchParam);

	Figures getDayOfWeekFigures(FingraphSearchParam searchParam);

	List<String> getOsVersionsDailyTopNList(FingraphSearchParam searchParam);

	List<String> getAppVersionsDailyTopNList(FingraphSearchParam searchParam);

	List<VersionsData> getOsVersionsDailyTopNSum(FingraphSearchParam searchParam);

	List<VersionsTimeSeriesData> getOsVersionsDailyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<VersionsData> getAppVersionsDailyTopNSum(FingraphSearchParam searchParam);

	List<VersionsTimeSeriesData> getAppVersionsDailyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getOsVersionsWeeklyTopNList(FingraphSearchParam searchParam);

	List<VersionsData> getOsVersionsWeeklyTopNSum(FingraphSearchParam searchParam);

	List<VersionsTimeSeriesData> getOsVersionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getAppVersionsWeeklyTopNList(FingraphSearchParam searchParam);

	List<VersionsData> getAppVersionsWeeklyTopNSum(FingraphSearchParam searchParam);

	List<VersionsTimeSeriesData> getAppVersionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getOsVersionsMonthlyTopNList(FingraphSearchParam searchParam);

	List<VersionsData> getOsVersionsMonthlyTopNSum(FingraphSearchParam searchParam);

	List<VersionsTimeSeriesData> getOsVersionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getAppVersionsMonthlyTopNList(FingraphSearchParam searchParam);

	List<VersionsData> getAppVersionsMonthlyTopNSum(FingraphSearchParam searchParam);

	List<VersionsTimeSeriesData> getAppVersionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getResolutionDailyTopNList(FingraphSearchParam searchParam);

	List<ResolutionData> getResolutionDailySum(FingraphSearchParam searchParam);

	List<ResolutionTimeSeriesData> getResolutionDailyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getResolutionWeeklyTopNList(FingraphSearchParam searchParam);

	List<ResolutionData> getResolutionWeeklySum(FingraphSearchParam searchParam);

	List<ResolutionTimeSeriesData> getResolutionWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);

	List<String> getResolutionMonthlyTopNList(FingraphSearchParam searchParam);

	List<ResolutionData> getResolutionMonthlySum(FingraphSearchParam searchParam);

	List<ResolutionTimeSeriesData> getResolutionMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);

}
