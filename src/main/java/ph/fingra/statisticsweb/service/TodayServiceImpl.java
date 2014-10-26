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

import ph.fingra.statisticsweb.dao.TodayDao;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.FrequencyData;
import ph.fingra.statisticsweb.domain.SessionLengthData;
import ph.fingra.statisticsweb.domain.TimeSeriesData;

@Service
public class TodayServiceImpl implements TodayService{

	@Autowired
	private TodayDao todayDao;
	
	@Override
	public Figures getNewUsersTimelyFigures(FingraphSearchParam searchParam) {
		return todayDao.getNewUsersTimelyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getNewUsersTimelyInfoList(FingraphSearchParam searchParam) {
		return todayDao.getNewUsersTimelyInfoList(searchParam);
	}

	@Override
	public Figures getActiveUsersTimelyFigures(FingraphSearchParam searchParam) {
		return todayDao.getActiveUsersTimelyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getActiveUsersTimelyInfoList(FingraphSearchParam searchParam) {
		return todayDao.getActiveUsersTimelyInfoList(searchParam);
	}

	@Override
	public Figures getPageViewsTimelyFigures(FingraphSearchParam searchParam) {
		return todayDao.getPageViewsTimelyFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getPageViewsTimelyInfoList(	FingraphSearchParam searchParam) {
		return todayDao.getPageViewsTimelyInfoList(searchParam);
	}

	@Override
	public Figures getSessionsTimelyfigures(FingraphSearchParam searchParam) {
		return todayDao.getSessionsTimelyfigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionTimelyInfoList(FingraphSearchParam searchParam) {
		return todayDao.getSessionTimelyInfoList(searchParam);
	}

	@Override
	public FrequencyData getFrequenceyTimelyRanges(FingraphSearchParam searchParam) {
		FrequencyData total = todayDao.getFrequencyTimelyRanges(searchParam);
		total.calculateTotal();
		return total;
	}

	@Override
	public List<FrequencyData> getSessionTimeSeriesList(FingraphSearchParam searchParam) {
		return todayDao.getSessionTimeSeriesList(searchParam);
	}

	@Override
	public Figures getSessionLengthFigures(FingraphSearchParam searchParam) {
		return todayDao.getSessionLengthFigures(searchParam);
	}

	@Override
	public List<TimeSeriesData> getSessionLengthList(FingraphSearchParam searchParam) {
		return todayDao.getSessionLengthList(searchParam);
	}

	@Override
	public List<SessionLengthData> getSessionLengthFrequenceyList(FingraphSearchParam searchParam) {
		return todayDao.getSessionLengthFrequencyList(searchParam);
	}

	@Override
	public SessionLengthData getSessionLengthFrequencey(FingraphSearchParam searchParam) {
		SessionLengthData total = todayDao.getSessionLengthFrequency(searchParam);
		total.calculateTotal();
		return total;
	}


}
