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
public class TodayDaoImpl implements TodayDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Figures getNewUsersTimelyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getNewUsersTimelyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersTimelyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getNewUsersTimelyInfoList", searchParam);
	}

	@Override
	public Figures getActiveUsersTimelyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getActiveUsersTimelyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersTimelyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getActiveUsersTimelyInfoList", searchParam);
	}

	@Override
	public Figures getPageViewsTimelyFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getPageViewsTimelyFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsTimelyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getPageViewsTimelyInfoList", searchParam);
	}

	@Override
	public Figures getSessionsTimelyfigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getSessionsTimelyfigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionTimelyInfoList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getSessionTimelyInfoList", searchParam);
	}

	@Override
	public FrequencyData getFrequencyTimelyRanges(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getFrequencyTimelyRanges", searchParam);
	}

	@Override
	public List<FrequencyData> getSessionTimeSeriesList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getSessionTimeSeriesList", searchParam);
	}

	@Override
	public Figures getSessionLengthFigures(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getSessionLengthFigures", searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthList(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getSessionLengthList", searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthFrequencyList(	FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectList("today.getSessionLengthFrequencyList", searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthFrequency(FingraphSearchParam searchParam) {
		return sqlSessionTemplate.selectOne("today.getSessionLengthFrequency",searchParam);
	}


}
