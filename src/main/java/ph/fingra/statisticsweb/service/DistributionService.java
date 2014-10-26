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

import ph.fingra.statisticsweb.domain.DayOfWeekData;
import ph.fingra.statisticsweb.domain.Figures;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.TimeOfDayData;
import ph.fingra.statisticsweb.domain.TopResolutionData;
import ph.fingra.statisticsweb.domain.TopVersionsData;

public interface DistributionService {

	List<DayOfWeekData> getDayOfWeekList(FingraphSearchParam searchParam);

	Figures getDayOfWeekFigures(FingraphSearchParam searchParam);

	TimeOfDayData getTimeOfDayList(FingraphSearchParam searchParam);

	Figures getTimeOfDayFigures(FingraphSearchParam searchParam);

	TopVersionsData getOsVesionsDailyData(FingraphSearchParam searchParam);

	TopVersionsData getAppVesionsDailyData(FingraphSearchParam searchParam);

	TopVersionsData getOsVesionsWeeklyData(FingraphSearchParam searchParam);

	TopVersionsData getAppVesionsWeeklyData(FingraphSearchParam searchParam);

	TopVersionsData getOsVesionsMonthlyData(FingraphSearchParam searchParam);

	TopVersionsData getAppVesionsMonthlyData(FingraphSearchParam searchParam);

	TopResolutionData getResolutionDailyData(FingraphSearchParam searchParam);

	TopResolutionData getResolutionWeeklyData(FingraphSearchParam searchParam);

	TopResolutionData getResolutionMonthlyData(FingraphSearchParam searchParam);

}
