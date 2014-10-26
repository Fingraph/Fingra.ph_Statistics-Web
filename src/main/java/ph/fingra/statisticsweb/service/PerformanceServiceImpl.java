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

import ph.fingra.statisticsweb.dao.PerformanceDao;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.FrequencyData;
import ph.fingra.statisticsweb.domain.SessionLengthData;
import ph.fingra.statisticsweb.domain.TimeSeriesData;

@Service
public class PerformanceServiceImpl implements PerformanceService {

	@Autowired
	private PerformanceDao performanceDao;

	@Override
	public Figures getNewUsersDailyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getNewUsersDailyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersDailyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getNewUsersDailyInfoList(searchParam);
	}

	@Override
	public Figures getNewUsersWeeklyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getNewUsersWeeklyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersWeeklyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getNewUsersWeeklyInfoList(searchParam);
	}

	@Override
	public Figures getNewUsersMonthlyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getNewUsersMonthlyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersMonthlyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getNewUsersMonthlyInfoList(searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthDailySectionList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthDailySectionList(searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthDailyTotal(FingraphSearchParam searchParam) {
		SessionLengthData total = performanceDao.getSessionLengthDailyTotal(searchParam);
		total.calculateTotal();
		return total;
	}

	@Override
	public List<TimeSeriesData> getSessionLengthDailyMedianList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthDailyMedianList(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthWeeklyMedianList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthWeeklyMedianList(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthMonthlyMedianList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthMonthlyMedianList(searchParam);
	}

	@Override
	public Figures getActiveUsersDailyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getActiveUsersDailyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersDailyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getActiveUsersDailyInfoList(searchParam);
	}

	@Override
	public Figures getActiveUsersWeeklyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getActiveUsersWeeklyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersWeeklyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getActiveUsersWeeklyInfoList(searchParam);
	}

	@Override
	public Figures getActiveUsersMonthlyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getActiveUsersMonthlyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersMonthlyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getActiveUsersMonthlyInfoList(searchParam);
	}

	@Override
	public Figures getPageViewsDailyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getPageViewsDailyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsDailyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getPageViewsDailyInfoList(searchParam);
	}

	@Override
	public Figures getPageViewsWeeklyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getPageViewsWeeklyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsWeeklyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getPageViewsWeeklyInfoList(searchParam);
	}

	@Override
	public Figures getPageViewsMonthlyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getPageViewsMonthlyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsMonthlyInfoList(FingraphSearchParam searchParam) {
		return performanceDao.getPageViewsMonthlyInfoList(searchParam);
	}

	@Override
	public FrequencyData getFrequenceyDailyRanges(FingraphSearchParam searchParam) {
		FrequencyData total = performanceDao.getFrequencyDailyRanges(searchParam);
		total.calculateTotal();
		return total;
	}

	@Override
	public FrequencyData getFrequenceyWeeklyRanges(FingraphSearchParam searchParam) {
		FrequencyData total = performanceDao.getFrequencyWeeklyRanges(searchParam);
		total.calculateTotal();
		return total;
	}

	@Override
	public FrequencyData getFrequenceyMonthlyRanges(FingraphSearchParam searchParam) {
		FrequencyData total = performanceDao.getFrequencyMonthlyRanges(searchParam);
		total.calculateTotal();
		return total;
	}

	@Override
	public List<FrequencyData> getTimeSeriesSessionsDailyList(FingraphSearchParam searchParam) {
		return performanceDao.getTimeSeriesSessionsDailyList(searchParam);
	}

	@Override
	public List<FrequencyData> getTimeSeriesSessionsWeeklyList(FingraphSearchParam searchParam) {
		return performanceDao.getTimeSeriesSessionsWeeklyList(searchParam);
	}

	@Override
	public List<FrequencyData> getTimeSeriesSessionsMonthlyList(FingraphSearchParam searchParam) {
		return performanceDao.getTimeSeriesSessionsMonthlyList(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionsDailyList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionsDailyList(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionsWeeklyList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionsWeeklyList(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionsMonthlyList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionsMonthlyList(searchParam);
	}

	@Override
	public Figures getSessionsFigures(FingraphSearchParam searchParam) {
		return performanceDao.getSessionsFigures(searchParam);
	}

	@Override
	public Figures getSessionLengthDailyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthDailyFigures(searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthWeeklyTotal(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthWeeklyTotal(searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthWeeklySectionList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthWeeklySectionList(searchParam);
	}

	@Override
	public Figures getSessionLengthWeeklyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthWeeklyFigures(searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthMonthlyTotal(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthMonthlyTotal(searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthMonthlySectionList(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthMonthlySectionList(searchParam);
	}

	@Override
	public Figures getSessionLengthMonthlyFigures(FingraphSearchParam searchParam) {
		return performanceDao.getSessionLengthMonthlyFigures(searchParam);
	}

}

