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

package ph.fingra.statisticsweb.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class TimeSeriesData implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -7987040686181073429L;
	private String year;
	private String month;
	private String week;
	private String date;
	private String fromDate;
	private String toDate;
	private BigDecimal value = BigDecimal.ZERO;
	private BigDecimal rate = BigDecimal.ZERO;

	public TimeSeriesData(){}

	public TimeSeriesData(String year, String month, String week, String date, String fromDate, String toDate, BigDecimal value, BigDecimal rate) {
		super();
		this.year = year;
		this.month = month;
		this.week = week;
		this.date = date;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.value = value;
		this.rate = rate;
	}
	
	public TimeSeriesData clone(){
		TimeSeriesData newData = new TimeSeriesData();
		
		newData.year = year;
		newData.month = month;
		newData.week = week;
		newData.date = date;
		newData.fromDate = fromDate;
		newData.toDate = toDate;
		if (value!= null) newData.setValue(new BigDecimal(value.toString()));
		if (rate!= null) newData.setRate(new BigDecimal(rate.toString()));
		
		/*
		if (year!= null) newData.setYear(new String(year.getBytes()));
		if (month!= null) newData.setMonth(new String(month.getBytes()));
		if (week!= null) newData.setWeek(new String(week.getBytes()));
		if (date!= null) newData.setDate(new String(date.getBytes()));
		if (fromDate!= null) newData.setFromDate(new String(fromDate.getBytes()));
		if (toDate!= null) newData.setToDate(new String(toDate.getBytes()));
		if (value!= null) newData.setValue(new BigDecimal(value.toString()));
		if (rate!= null) newData.setRate(new BigDecimal(rate.toString()));
		*/
		
		return newData;
	}
	
	public void setValue(BigDecimal value){
		this.value = value;
	}
	
	public void setRate(BigDecimal rate){
		this.rate = rate;
	}

}
