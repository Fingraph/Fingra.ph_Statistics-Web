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
    
    List<CountryData> getCountrySessionsDailySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountrySessionsDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryActiveUsersDailySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryActiveUsersDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryPageViewsDailySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryPageViewsDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountrySessionLengthDailySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountrySessionLengthDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountrySessionsWeeklySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountrySessionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryActiveUsersWeeklySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryActiveUsersWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountrySessionLengthWeeklySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountrySessionLengthWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryPageViewsWeeklySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryPageViewsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountrySessionsMonthlySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountrySessionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryActiveUsersMonthlySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryActiveUsersMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountrySessionLengthMonthlySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountrySessionLengthMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryPageViewsMonthlySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryPageViewsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryNewUsersDailySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryNewUsersDailyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryNewUsersWeeklySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryNewUsersWeeklyTimeSeriesDataList(FingraphSearchParam searchParam);
    
    List<CountryData> getCountryNewUsersMonthlySumList(FingraphSearchParam searchParam);
    
    List<CountryTimeSeriesData> getCountryNewUsersMonthlyTimeSeriesDataList(FingraphSearchParam searchParam);
}
