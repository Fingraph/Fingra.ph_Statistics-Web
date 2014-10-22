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
    
    // New Users ==============================================================
    
    public List<TimeSeriesData> getNewUsersDailyInfoList(FingraphSearchParam searchParam);
    
    public Figures getNewUsersDailyFigures(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getNewUsersWeeklyInfoList(FingraphSearchParam searchParam);
    
    public Figures getNewUsersWeeklyFigures(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getNewUsersMonthlyInfoList(FingraphSearchParam searchParam);
    
    public Figures getNewUsersMonthlyFigures(FingraphSearchParam searchParam);
    
    // Active Users ===========================================================
    
    public List<TimeSeriesData> getActiveUsersDailyInfoList(FingraphSearchParam searchParam);
    
    public Figures getActiveUsersDailyFigures(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getActiveUsersWeeklyInfoList(FingraphSearchParam searchParam);
    
    public Figures getActiveUsersWeeklyFigures(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getActiveUsersMonthlyInfoList(FingraphSearchParam searchParam);
    
    public Figures getActiveUsersMonthlyFigures(FingraphSearchParam searchParam);
    
    // Sessions (1) Sessions Frequencys =======================================
    
    public Figures getSessionsFigures(FingraphSearchParam searchParam);
    
    public List<FrequencyData> getTimeSeriesSessionsDailyList(FingraphSearchParam searchParam);
    
    public FrequencyData getFrequencyDailyRanges(FingraphSearchParam searchParam);
    
    public List<FrequencyData> getTimeSeriesSessionsWeeklyList(FingraphSearchParam searchParam);
    
    public FrequencyData getFrequencyWeeklyRanges(FingraphSearchParam searchParam);
    
    public List<FrequencyData> getTimeSeriesSessionsMonthlyList(FingraphSearchParam searchParam);
    
    public FrequencyData getFrequencyMonthlyRanges(FingraphSearchParam searchParam);
    
    // Sessions (2) Total Sessions ============================================
    
    public List<TimeSeriesData> getSessionsDailyList(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getSessionsWeeklyList(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getSessionsMonthlyList(FingraphSearchParam searchParam);
    
    // Session Length (1) Total ===============================================
    
    public SessionLengthData getSessionLengthDailyTotal(FingraphSearchParam searchParam);
    
    public List<SessionLengthData> getSessionLengthDailySectionList(FingraphSearchParam searchParam);
    
    public Figures getSessionLengthDailyFigures(FingraphSearchParam searchParam);
    
    public SessionLengthData getSessionLengthWeeklyTotal(FingraphSearchParam searchParam);
    
    public List<SessionLengthData> getSessionLengthWeeklySectionList(FingraphSearchParam searchParam);
    
    public Figures getSessionLengthWeeklyFigures(FingraphSearchParam searchParam);
    
    public SessionLengthData getSessionLengthMonthlyTotal(FingraphSearchParam searchParam);
    
    public List<SessionLengthData> getSessionLengthMonthlySectionList(FingraphSearchParam searchParam);
    
    public Figures getSessionLengthMonthlyFigures(FingraphSearchParam searchParam);
    
    // Session Length (2) Median ==============================================
    
    public List<TimeSeriesData> getSessionLengthDailyMedianList(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getSessionLengthWeeklyMedianList(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getSessionLengthMonthlyMedianList(FingraphSearchParam searchParam);
    
    // Page Views =============================================================
    
    public List<TimeSeriesData> getPageViewsDailyInfoList(FingraphSearchParam searchParam);
    
    public Figures getPageViewsDailyFigures(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getPageViewsWeeklyInfoList(FingraphSearchParam searchParam);
    
    public Figures getPageViewsWeeklyFigures(FingraphSearchParam searchParam);
    
    public List<TimeSeriesData> getPageViewsMonthlyInfoList(FingraphSearchParam searchParam);
    
    public Figures getPageViewsMonthlyFigures(FingraphSearchParam searchParam);
}
