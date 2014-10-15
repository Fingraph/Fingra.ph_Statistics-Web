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

public interface TodayDao {

	Figures getNewUsersTimelyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getNewUsersTimelyInfoList(FingraphSearchParam searchParam);

	Figures getActiveUsersTimelyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getActiveUsersTimelyInfoList(FingraphSearchParam searchParam);

	Figures getPageViewsTimelyFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getPageViewsTimelyInfoList(FingraphSearchParam searchParam);

	Figures getSessionsTimelyfigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionTimelyInfoList(FingraphSearchParam searchParam);

	FrequencyData getFrequencyTimelyRanges(FingraphSearchParam searchParam);

	List<FrequencyData> getSessionTimeSeriesList(FingraphSearchParam searchParam);

	Figures getSessionLengthFigures(FingraphSearchParam searchParam);

	List<TimeSeriesData> getSessionLengthList(FingraphSearchParam searchParam);

	List<SessionLengthData> getSessionLengthFrequencyList(FingraphSearchParam searchParam);

	SessionLengthData getSessionLengthFrequency(FingraphSearchParam searchParam);


}
