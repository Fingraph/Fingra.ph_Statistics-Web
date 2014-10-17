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

public class FrequencyData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -5838790327088381375L;
    
    private String year;
    private String month;
    private String day;
    private String appkey;
    private String fromDate;
    private String toDate;
    
    private String date;
    private int freq_user_1;
    private int freq_user_2;
    private int freq_user_3_4;
    private int freq_user_5_6;
    private int freq_user_7_9;
    private int freq_user_10_14;
    private int freq_user_15_19;
    private int freq_user_20_49;
    private int freq_user_50_99;
    private int freq_user_100_499;
    private int freq_user_over_500;
    private int total;
    
    // total for calculate percentage at bar chart
    public void calculateTotal(){
        this.total = this.freq_user_1 + this.freq_user_2 + this.freq_user_3_4
                + this.freq_user_5_6 + this.freq_user_7_9 + this.freq_user_10_14
                + this.freq_user_15_19 + this.freq_user_20_49 + this.freq_user_50_99
                + this.freq_user_100_499 + this.freq_user_over_500;
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
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getFreq_user_1() {
        return freq_user_1;
    }
    public void setFreq_user_1(int freq_user_1) {
        this.freq_user_1 = freq_user_1;
    }
    public int getFreq_user_2() {
        return freq_user_2;
    }
    public void setFreq_user_2(int freq_user_2) {
        this.freq_user_2 = freq_user_2;
    }
    public int getFreq_user_3_4() {
        return freq_user_3_4;
    }
    public void setFreq_user_3_4(int freq_user_3_4) {
        this.freq_user_3_4 = freq_user_3_4;
    }
    public int getFreq_user_5_6() {
        return freq_user_5_6;
    }
    public void setFreq_user_5_6(int freq_user_5_6) {
        this.freq_user_5_6 = freq_user_5_6;
    }
    public int getFreq_user_7_9() {
        return freq_user_7_9;
    }
    public void setFreq_user_7_9(int freq_user_7_9) {
        this.freq_user_7_9 = freq_user_7_9;
    }
    public int getFreq_user_10_14() {
        return freq_user_10_14;
    }
    public void setFreq_user_10_14(int freq_user_10_14) {
        this.freq_user_10_14 = freq_user_10_14;
    }
    public int getFreq_user_15_19() {
        return freq_user_15_19;
    }
    public void setFreq_user_15_19(int freq_user_15_19) {
        this.freq_user_15_19 = freq_user_15_19;
    }
    public int getFreq_user_20_49() {
        return freq_user_20_49;
    }
    public void setFreq_user_20_49(int freq_user_20_49) {
        this.freq_user_20_49 = freq_user_20_49;
    }
    public int getFreq_user_50_99() {
        return freq_user_50_99;
    }
    public void setFreq_user_50_99(int freq_user_50_99) {
        this.freq_user_50_99 = freq_user_50_99;
    }
    public int getFreq_user_100_499() {
        return freq_user_100_499;
    }
    public void setFreq_user_100_499(int freq_user_100_499) {
        this.freq_user_100_499 = freq_user_100_499;
    }
    public int getFreq_user_over_500() {
        return freq_user_over_500;
    }
    public void setFreq_user_over_500(int freq_user_over_500) {
        this.freq_user_over_500 = freq_user_over_500;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
