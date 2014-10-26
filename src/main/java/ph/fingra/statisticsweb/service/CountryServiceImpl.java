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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.fingra.statisticsweb.dao.CountryDao;
import ph.fingra.statisticsweb.domain.CountryData;
import ph.fingra.statisticsweb.domain.CountryTimeSeriesData;
import ph.fingra.statisticsweb.domain.FingraphSearchParam;
import ph.fingra.statisticsweb.domain.TopCountryData;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	public TopCountryData getCountrySessionsDailyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountrySessionsDailySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountrySessionsDailyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryActiveUsersDailyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryActiveUsersDailySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryActiveUsersDailyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryPageViewsDailyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryPageViewsDailySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.
			
			List<CountryTimeSeriesData> ctsList = countryDao.getCountryPageViewsDailyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountrySessionLengthDailyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountrySessionLengthDailySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountrySessionLengthDailyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountrySessionsWeeklyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountrySessionsWeeklySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountrySessionsWeeklyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryActiveUsersWeeklyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryActiveUsersWeeklySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryActiveUsersWeeklyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountrySessionLengthWeeklyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountrySessionLengthWeeklySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){// Get top5 countries from all countries.
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountrySessionLengthWeeklyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryPageViewsWeeklyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryPageViewsWeeklySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryPageViewsWeeklyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountrySessionsMonthlyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountrySessionsMonthlySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountrySessionsMonthlyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryActiveUsersMonthlyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryActiveUsersMonthlySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryActiveUsersMonthlyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountrySessionLengthMonthlyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountrySessionLengthMonthlySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountrySessionLengthMonthlyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryPageViewsMonthlyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryPageViewsMonthlySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryPageViewsMonthlyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryNewUsersDailyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryNewUsersDailySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryNewUsersDailyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryNewUsersWeeklyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryNewUsersWeeklySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryNewUsersWeeklyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}

	@Override
	public TopCountryData getCountryNewUsersMonthlyData(FingraphSearchParam searchParam) {
		TopCountryData data = new TopCountryData();

		List<CountryData> sumList = countryDao.getCountryNewUsersMonthlySumList(searchParam);
		if(sumList.size()>0){
			data.setSumList(sumList);

			List<String> topNList = new ArrayList<String>();
			if(sumList.size()>5){//전체국가중 top5 추출
				for(int i=0 ; i<5 ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}else{
				for(int i=0 ; i<sumList.size() ; i++){
					topNList.add(i, sumList.get(i).getIso2());
				}
			}
			searchParam.setTopNList(topNList);// set top5 country to the search list.
			data.setTopNList(topNList);// set top5 country to the json data set.

			List<CountryTimeSeriesData> ctsList = countryDao.getCountryNewUsersMonthlyTimeSeriesDataList(searchParam);
			data.setCtsList(ctsList);
		}
		return data;
	}
}
