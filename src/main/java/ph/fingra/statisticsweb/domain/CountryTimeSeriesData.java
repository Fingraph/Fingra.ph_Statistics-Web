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

public class CountryTimeSeriesData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 2412436120833031036L;
    
    private String year;
    private String month;
    private String week;
    private String date;
    private String fromDate;
    private String toDate;
    
    private int top0;
    private int top1;
    private int top2;
    private int top3;
    private int top4;
    
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public int getTop0() {
        return top0;
    }
    public void setTop0(int top0) {
        this.top0 = top0;
    }
    public int getTop1() {
        return top1;
    }
    public void setTop1(int top1) {
        this.top1 = top1;
    }
    public int getTop2() {
        return top2;
    }
    public void setTop2(int top2) {
        this.top2 = top2;
    }
    public int getTop3() {
        return top3;
    }
    public void setTop3(int top3) {
        this.top3 = top3;
    }
    public int getTop4() {
        return top4;
    }
    public void setTop4(int top4) {
        this.top4 = top4;
    }
}
