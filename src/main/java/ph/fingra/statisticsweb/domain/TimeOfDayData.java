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

public class TimeOfDayData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -2660433755063566539L;
    
    private String appkey;
    private String eventkey;
    private int groupkey;
    private String name;
    private String shortname;
    
    private int zero_session;
    private int one_session;
    private int two_session;
    private int three_session;
    private int four_session;
    private int five_session;
    private int six_session;
    private int seven_session;
    private int eight_session;
    private int nine_session;
    private int ten_session;
    private int eleven_session;
    private int twelve_session;
    private int thirteen_session;
    private int fourteen_session;
    private int fifteen_session;
    private int sixteen_session;
    private int seventeen_session;
    private int eighteen_session;
    private int nineteen_session;
    private int twenty_session;
    private int twentyone_session;
    private int twentytwo_session;
    private int twentythree_session;
    private int total;
    
    //total for calcuate percentage at bar chart
    public void calculateTotal(){
        this.total = this.zero_session + this.one_session + this.two_session + this.three_session + this.four_session + this.five_session
                + this.six_session + this.seven_session + this.eight_session + this.nine_session + this.ten_session
                + this.eleven_session + this.twelve_session + this.thirteen_session + this.fourteen_session + this.fifteen_session
                + this.sixteen_session + this.seventeen_session + this.eighteen_session + this.nineteen_session + this.twenty_session
                + this.twentyone_session + this.twentytwo_session + this.twentythree_session;
    }
    
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getEventkey() {
        return eventkey;
    }
    public void setEventkey(String eventkey) {
        this.eventkey = eventkey;
    }
    public int getGroupkey() {
        return groupkey;
    }
    public void setGroupkey(int groupkey) {
        this.groupkey = groupkey;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortname() {
        return shortname;
    }
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public int getZero_session() {
        return zero_session;
    }
    public void setZero_session(int zero_session) {
        this.zero_session = zero_session;
    }
    public int getOne_session() {
        return one_session;
    }
    public void setOne_session(int one_session) {
        this.one_session = one_session;
    }
    public int getTwo_session() {
        return two_session;
    }
    public void setTwo_session(int two_session) {
        this.two_session = two_session;
    }
    public int getThree_session() {
        return three_session;
    }
    public void setThree_session(int three_session) {
        this.three_session = three_session;
    }
    public int getFour_session() {
        return four_session;
    }
    public void setFour_session(int four_session) {
        this.four_session = four_session;
    }
    public int getFive_session() {
        return five_session;
    }
    public void setFive_session(int five_session) {
        this.five_session = five_session;
    }
    public int getSix_session() {
        return six_session;
    }
    public void setSix_session(int six_session) {
        this.six_session = six_session;
    }
    public int getSeven_session() {
        return seven_session;
    }
    public void setSeven_session(int seven_session) {
        this.seven_session = seven_session;
    }
    public int getEight_session() {
        return eight_session;
    }
    public void setEight_session(int eight_session) {
        this.eight_session = eight_session;
    }
    public int getNine_session() {
        return nine_session;
    }
    public void setNine_session(int nine_session) {
        this.nine_session = nine_session;
    }
    public int getTen_session() {
        return ten_session;
    }
    public void setTen_session(int ten_session) {
        this.ten_session = ten_session;
    }
    public int getEleven_session() {
        return eleven_session;
    }
    public void setEleven_session(int eleven_session) {
        this.eleven_session = eleven_session;
    }
    public int getTwelve_session() {
        return twelve_session;
    }
    public void setTwelve_session(int twelve_session) {
        this.twelve_session = twelve_session;
    }
    public int getThirteen_session() {
        return thirteen_session;
    }
    public void setThirteen_session(int thirteen_session) {
        this.thirteen_session = thirteen_session;
    }
    public int getFourteen_session() {
        return fourteen_session;
    }
    public void setFourteen_session(int fourteen_session) {
        this.fourteen_session = fourteen_session;
    }
    public int getFifteen_session() {
        return fifteen_session;
    }
    public void setFifteen_session(int fifteen_session) {
        this.fifteen_session = fifteen_session;
    }
    public int getSixteen_session() {
        return sixteen_session;
    }
    public void setSixteen_session(int sixteen_session) {
        this.sixteen_session = sixteen_session;
    }
    public int getSeventeen_session() {
        return seventeen_session;
    }
    public void setSeventeen_session(int seventeen_session) {
        this.seventeen_session = seventeen_session;
    }
    public int getEighteen_session() {
        return eighteen_session;
    }
    public void setEighteen_session(int eighteen_session) {
        this.eighteen_session = eighteen_session;
    }
    public int getNineteen_session() {
        return nineteen_session;
    }
    public void setNineteen_session(int nineteen_session) {
        this.nineteen_session = nineteen_session;
    }
    public int getTwenty_session() {
        return twenty_session;
    }
    public void setTwenty_session(int twenty_session) {
        this.twenty_session = twenty_session;
    }
    public int getTwentyone_session() {
        return twentyone_session;
    }
    public void setTwentyone_session(int twentyone_session) {
        this.twentyone_session = twentyone_session;
    }
    public int getTwentytwo_session() {
        return twentytwo_session;
    }
    public void setTwentytwo_session(int twentytwo_session) {
        this.twentytwo_session = twentytwo_session;
    }
    public int getTwentythree_session() {
        return twentythree_session;
    }
    public void setTwentythree_session(int twentythree_session) {
        this.twentythree_session = twentythree_session;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
}
