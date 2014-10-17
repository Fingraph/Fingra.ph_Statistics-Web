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

public class SessionLengthData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 6656569465216688390L;
    
    private String year;
    private String month;
    private String day;
    private String week;
    private String appkey;
    private String date;
    private String fromDate;
    private String toDate;
    
    private int under_three_sec;
    private int three_ten_sec;
    private int ten_thirty_sec;
    private int thirty_sixty_sec;
    private int one_three_min;
    private int three_ten_min;
    private int ten_thirty_min;
    private int over_thirty_min;
    private int total;
    
    // total for calcuate percentage at bar chart
    public void calculateTotal(){
        this.total = this.under_three_sec + this.three_ten_sec + this.ten_thirty_sec
                + this.thirty_sixty_sec + this.one_three_min + this.three_ten_min
                + this.ten_thirty_min + this.over_thirty_min;
    }
    
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
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getWeek() {
        return week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
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
    public int getUnder_three_sec() {
        return under_three_sec;
    }
    public void setUnder_three_sec(int under_three_sec) {
        this.under_three_sec = under_three_sec;
    }
    public int getThree_ten_sec() {
        return three_ten_sec;
    }
    public void setThree_ten_sec(int three_ten_sec) {
        this.three_ten_sec = three_ten_sec;
    }
    public int getTen_thirty_sec() {
        return ten_thirty_sec;
    }
    public void setTen_thirty_sec(int ten_thirty_sec) {
        this.ten_thirty_sec = ten_thirty_sec;
    }
    public int getThirty_sixty_sec() {
        return thirty_sixty_sec;
    }
    public void setThirty_sixty_sec(int thirty_sixty_sec) {
        this.thirty_sixty_sec = thirty_sixty_sec;
    }
    public int getOne_three_min() {
        return one_three_min;
    }
    public void setOne_three_min(int one_three_min) {
        this.one_three_min = one_three_min;
    }
    public int getThree_ten_min() {
        return three_ten_min;
    }
    public void setThree_ten_min(int three_ten_min) {
        this.three_ten_min = three_ten_min;
    }
    public int getTen_thirty_min() {
        return ten_thirty_min;
    }
    public void setTen_thirty_min(int ten_thirty_min) {
        this.ten_thirty_min = ten_thirty_min;
    }
    public int getOver_thirty_min() {
        return over_thirty_min;
    }
    public void setOver_thirty_min(int over_thirty_min) {
        this.over_thirty_min = over_thirty_min;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
