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

import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.FrequencyData;
import ph.fingra.statisticsweb.domain.SessionLengthData;
import ph.fingra.statisticsweb.domain.TimeSeriesData;

@Repository
public class PerformanceDaoImpl implements PerformanceDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Figures getNewUsersDailyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getNewUsersDailyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersDailyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getNewUsersDailyInfoList", searchParam);
	}

	@Override
	public Figures getNewUsersWeeklyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getNewUsersWeeklyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getgetNewUsersWeeklyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getgetNewUsersWeeklyInfoList", searchParam);
	}

	@Override
	public Figures getNewUsersMonthlyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getNewUsersMonthlyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersMonthlyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getNewUsersMonthlyInfoList", searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthDailySectionList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionLengthDailySectionList", searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthDailyTotal(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionLengthDailyTotal", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthDailyMedianList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionLengthDailyMedianList", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthWeeklyMedianList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionLengthWeeklyMedianList", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthMonthlyMedianList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionLengthMonthlyMedianList", searchParam);
	}

	@Override
	public Figures getActiveUsersDailyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getActiveUsersDailyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersDailyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getActiveUsersDailyInfoList", searchParam);
	}

	@Override
	public Figures getActiveUsersWeeklyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getActiveUsersWeeklyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersWeeklyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getActiveUsersWeeklyInfoList", searchParam);
	}

	@Override
	public Figures getActiveUsersMonthlyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getActiveUsersMonthlyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersMonthlyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getActiveUsersMonthlyInfoList", searchParam);
	}

	@Override
	public Figures getPageViewsDailyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getPageViewsDailyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsDailyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getPageViewsDailyInfoList", searchParam);
	}

	@Override
	public Figures getPageViewsWeeklyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getPageViewsWeeklyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsWeeklyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getPageViewsWeeklyInfoList", searchParam);
	}

	@Override
	public Figures getPageViewsMonthlyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getPageViewsMonthlyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsMonthlyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getPageViewsMonthlyInfoList", searchParam);
	}

	@Override
	public FrequencyData getFrequencyDailyRanges(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getFrequenceyDailyRanges", searchParam);
	}

	@Override
	public FrequencyData getFrequencyWeeklyRanges(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getFrequenceyWeeklyRanges", searchParam);
	}

	@Override
	public FrequencyData getFrequencyMonthlyRanges(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getFrequenceyMonthlyRanges", searchParam);
	}

	@Override
	public List<FrequencyData> getTimeSeriesSessionsDailyList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getTimeSeriesSessionsDailyList", searchParam);
	}

	@Override
	public List<FrequencyData> getTimeSeriesSessionsWeeklyList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getTimeSeriesSessionsWeeklyList", searchParam);
	}

	@Override
	public List<FrequencyData> getTimeSeriesSessionsMonthlyList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getTimeSeriesSessionsMonthlyList", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionsDailyList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionsDailyList", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionsWeeklyList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionsWeeklyList", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionsMonthlyList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionsMonthlyList", searchParam);
	}

	@Override
	public Figures getSessionsFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionsFigures", searchParam);
	}

	@Override
	public Figures getSessionLengthDailyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionLengthDailyFigures", searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthWeeklyTotal(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionLengthWeeklyTotal", searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthWeeklySectionList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionLengthWeeklySectionList", searchParam);
	}

	@Override
	public Figures getSessionLengthWeeklyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionLengthWeeklyFigures", searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthMonthlyTotal(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionLengthMonthlyTotal", searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthMonthlySectionList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("performance.getSessionLengthMonthlySectionList", searchParam);
	}

	@Override
	public Figures getSessionLengthMonthlyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("performance.getSessionLengthMonthlyFigures", searchParam);
	}

}
