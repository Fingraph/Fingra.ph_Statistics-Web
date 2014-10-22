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
    
    // Day Of Week ============================================================
    
    public List<DayOfWeekData> getDayOfWeekList(FingraphSearchParam searchParam);
    
    public Figures getDayOfWeekFigures(FingraphSearchParam searchParam);
    
    // Time Of Day ============================================================
    
    public TimeOfDayData getTimeOfDayList(FingraphSearchParam searchParam);
    
    public Figures getTimeOfDayFigures(FingraphSearchParam searchParam);
    
    // Top Resolution =========================================================
    
    public List<String> getResolutionDailyTopNList(FingraphSearchParam searchParam);
    
    public List<ResolutionData> getResolutionDailySum(FingraphSearchParam searchParam);
    
    public List<ResolutionTimeSeriesData> getResolutionDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<String> getResolutionWeeklyTopNList(FingraphSearchParam searchParam);
    
    public List<ResolutionData> getResolutionWeeklySum(FingraphSearchParam searchParam);
    
    public List<ResolutionTimeSeriesData> getResolutionWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<String> getResolutionMonthlyTopNList(FingraphSearchParam searchParam);
    
    public List<ResolutionData> getResolutionMonthlySum(FingraphSearchParam searchParam);
    
    public List<ResolutionTimeSeriesData> getResolutionMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    // Top Versions : App Versions ============================================
    
    public List<String> getAppVersionsDailyTopNList(FingraphSearchParam searchParam);
    
    public List<VersionsData> getAppVersionsDailyTopNSum(FingraphSearchParam searchParam);
    
    public List<VersionsTimeSeriesData> getAppVersionsDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<String> getAppVersionsWeeklyTopNList(FingraphSearchParam searchParam);
    
    public List<VersionsData> getAppVersionsWeeklyTopNSum(FingraphSearchParam searchParam);
    
    public List<VersionsTimeSeriesData> getAppVersionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<String> getAppVersionsMonthlyTopNList(FingraphSearchParam searchParam);
    
    public List<VersionsData> getAppVersionsMonthlyTopNSum(FingraphSearchParam searchParam);
    
    public List<VersionsTimeSeriesData> getAppVersionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    // Top Versions : OS Versions =============================================
    
    public List<String> getOsVersionsDailyTopNList(FingraphSearchParam searchParam);
    
    public List<VersionsData> getOsVersionsDailyTopNSum(FingraphSearchParam searchParam);
    
    public List<VersionsTimeSeriesData> getOsVersionsDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<String> getOsVersionsWeeklyTopNList(FingraphSearchParam searchParam);
    
    public List<VersionsData> getOsVersionsWeeklyTopNSum(FingraphSearchParam searchParam);
    
    public List<VersionsTimeSeriesData> getOsVersionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<String> getOsVersionsMonthlyTopNList(FingraphSearchParam searchParam);
    
    public List<VersionsData> getOsVersionsMonthlyTopNSum(FingraphSearchParam searchParam);
    
    public List<VersionsTimeSeriesData> getOsVersionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
}
