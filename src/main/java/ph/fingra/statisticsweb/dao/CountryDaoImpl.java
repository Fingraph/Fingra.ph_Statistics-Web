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

import ph.fingra.statisticsweb.domain.CountryData;
import ph.fingra.statisticsweb.domain.CountryTimeSeriesData;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;

@Repository
public class CountryDaoImpl implements CountryDao {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    // Top Countries : Sessions ===============================================
    
    @Override
    public List<CountryData> getCountrySessionsDailySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionsDailySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountrySessionsDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionsDailyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountrySessionsWeeklySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionsWeeklySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountrySessionsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionsWeeklyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountrySessionsMonthlySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionsMonthlySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountrySessionsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionsMonthlyTimeSeriesDataList", searchParam);
    }
    
    // Top Countries : Active Users ===========================================
    
    @Override
    public List<CountryData> getCountryActiveUsersDailySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryActiveUsersDailySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryActiveUsersDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryActiveUsersDailyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountryActiveUsersWeeklySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryActiveUsersWeeklySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryActiveUsersWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryActiveUsersWeeklyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountryActiveUsersMonthlySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryActiveUsersMonthlySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryActiveUsersMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryActiveUsersMonthlyTimeSeriesDataList", searchParam);
    }
    
    // Top Countries : Session Length =========================================
    
    @Override
    public List<CountryData> getCountrySessionLengthDailySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionLengthDailySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountrySessionLengthDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionLengthDailyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountrySessionLengthWeeklySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionLengthWeeklySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountrySessionLengthWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionLengthWeeklyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountrySessionLengthMonthlySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionLengthMonthlySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountrySessionLengthMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountrySessionLengthMonthlyTimeSeriesDataList", searchParam);
    }
    
    // Top Countries : Page Views =============================================
    
    @Override
    public List<CountryData> getCountryPageViewsDailySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryPageViewsDailySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryPageViewsDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryPageViewsDailyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountryPageViewsWeeklySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryPageViewsWeeklySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryPageViewsWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryPageViewsWeeklyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountryPageViewsMonthlySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryPageViewsMonthlySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryPageViewsMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryPageViewsMonthlyTimeSeriesDataList", searchParam);
    }
    
    // Top Countries : Active Users ===========================================
    
    @Override
    public List<CountryData> getCountryNewUsersDailySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryNewUsersDailySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryNewUsersDailyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryNewUsersDailyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountryNewUsersWeeklySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryNewUsersWeeklySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryNewUsersWeeklyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryNewUsersWeeklyTimeSeriesDataList", searchParam);
    }
    
    @Override
    public List<CountryData> getCountryNewUsersMonthlySumList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryNewUsersMonthlySumList", searchParam);
    }
    
    @Override
    public List<CountryTimeSeriesData> getCountryNewUsersMonthlyTimeSeriesDataList(FingraphSearchParam searchParam) {
        return sqlSessionTemplate.selectList("country.getCountryNewUsersMonthlyTimeSeriesDataList", searchParam);
    }
}
