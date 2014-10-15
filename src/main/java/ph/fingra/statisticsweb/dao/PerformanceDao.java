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

import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.FrequencyData;
import ph.fingra.statisticsweb.domain.SessionLengthData;
import ph.fingra.statisticsweb.domain.TimeSeriesData;

public interface PerformanceDao {

	Figures getNewUsersDailyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getNewUsersDailyInfoList(FingraphSearchParam searchParam);

	Figures getNewUsersWeeklyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getgetNewUsersWeeklyInfoList(FingraphSearchParam searchParam);

	Figures getNewUsersMonthlyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getNewUsersMonthlyInfoList(FingraphSearchParam searchParam);

	List<SessionLengthData> getSessionLengthDailySectionList(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionLengthDailyMedianList(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionLengthWeeklyMedianList(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionLengthMonthlyMedianList(FingraphSearchParam searchParam);

	Figures getActiveUsersDailyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getActiveUsersDailyInfoList(FingraphSearchParam searchParam);

	Figures getActiveUsersWeeklyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getActiveUsersWeeklyInfoList(FingraphSearchParam searchParam);

	Figures getActiveUsersMonthlyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getActiveUsersMonthlyInfoList(FingraphSearchParam searchParam);

	Figures getPageViewsDailyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getPageViewsDailyInfoList(FingraphSearchParam searchParam);

	Figures getPageViewsWeeklyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getPageViewsWeeklyInfoList(FingraphSearchParam searchParam);

	Figures getPageViewsMonthlyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getPageViewsMonthlyInfoList(FingraphSearchParam searchParam);

	FrequencyData getFrequencyDailyRanges(FingraphSearchParam searchParam);

	FrequencyData getFrequencyWeeklyRanges(FingraphSearchParam searchParam);

	FrequencyData getFrequencyMonthlyRanges(FingraphSearchParam searchParam);

	List<FrequencyData> getTimeSeriesSessionsDailyList(FingraphSearchParam searchParam);

	List<FrequencyData> getTimeSeriesSessionsWeeklyList(FingraphSearchParam searchParam);

	List<FrequencyData> getTimeSeriesSessionsMonthlyList(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionsDailyList(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionsWeeklyList(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionsMonthlyList(FingraphSearchParam searchParam);

	Figures getSessionsFigures(FingraphSearchParam searchParam);

	SessionLengthData getSessionLengthDailyTotal(FingraphSearchParam searchParam);

	Figures getSessionLengthDailyFigures(FingraphSearchParam searchParam);

	SessionLengthData getSessionLengthWeeklyTotal(FingraphSearchParam searchParam);

	List<SessionLengthData> getSessionLengthWeeklySectionList(FingraphSearchParam searchParam);

	Figures getSessionLengthWeeklyFigures(FingraphSearchParam searchParam);

	SessionLengthData getSessionLengthMonthlyTotal(FingraphSearchParam searchParam);

	List<SessionLengthData> getSessionLengthMonthlySectionList(FingraphSearchParam searchParam);

	Figures getSessionLengthMonthlyFigures(FingraphSearchParam searchParam);

}
