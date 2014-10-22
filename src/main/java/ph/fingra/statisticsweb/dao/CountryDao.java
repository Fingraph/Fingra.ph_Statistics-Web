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

import ph.fingra.statisticsweb.domain.CountryData;
import ph.fingra.statisticsweb.domain.CountryTimeSeriesData;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;

public interface CountryDao {
    
    // Top Countries : Sessions ===============================================
    
    public List<CountryData> getCountrySessionsDailySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountrySessionsDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountrySessionsWeeklySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountrySessionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountrySessionsMonthlySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountrySessionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    // Top Countries : Active Users ===========================================
    
    public List<CountryData> getCountryActiveUsersDailySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryActiveUsersDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountryActiveUsersWeeklySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryActiveUsersWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountryActiveUsersMonthlySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryActiveUsersMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    // Top Countries : Session Length =========================================
    
    public List<CountryData> getCountrySessionLengthDailySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountrySessionLengthDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountrySessionLengthWeeklySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountrySessionLengthWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountrySessionLengthMonthlySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountrySessionLengthMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    // Top Countries : Page Views =============================================
    
    public List<CountryData> getCountryPageViewsDailySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryPageViewsDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountryPageViewsWeeklySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryPageViewsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountryPageViewsMonthlySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryPageViewsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    // Top Countries : Active Users ===========================================
    
    public List<CountryData> getCountryNewUsersDailySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryNewUsersDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountryNewUsersWeeklySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryNewUsersWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    public List<CountryData> getCountryNewUsersMonthlySumList(FingraphSearchParam searchParam);
    
    public List<CountryTimeSeriesData> getCountryNewUsersMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
}
