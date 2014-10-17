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
import java.util.List;
import java.util.Map;

public class DashBoard extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 8892883125046436502L;
    
    private String appkey;
    private Boolean inData;
    private CurrPrevNumericValue todayNewUsers;
    private CurrPrevNumericValue todayActiveUsers;
    private CurrPrevNumericValue todaySessions;
    private CurrPrevNumericValue todaySessionLength;
    private CurrPrevNumericValue todayPageViews;
    
    private boolean compare;
    private CurrPrevNumericValue newUsers;
    private CurrPrevNumericValue activeUsers;
    private CurrPrevNumericValue sessions;
    private CurrPrevNumericValue sessionLength;
    private CurrPrevNumericValue pageViews;
    
    private String dayOfWeek;
    private String timeOfDay;
    private List<Map<String,BigDecimal>> topContries;
    
    private CurrPrevTextValue topResolution;
    private CurrPrevTextValue topAppVersion;
    private CurrPrevTextValue topOsVersion;
    
    private ComponentsInfo componentsInfo;
    private List<ComponentsGroup> componentGrpList;
    
    //PERFORMANCE SNAPSHOT thisWeek
    private String thisWeek;
    
    //TODAY SNAPSHOT todayTime
    private String todayNowTime;
    private String todayPrevTime;
    private String today;
    private String yesterday;
    
    public String getDayOfWeek(){
        return this.dayOfWeek==null?"N/A":this.dayOfWeek;
    }
    public String getTimeOfDay(){
        return this.timeOfDay==null?"N/A":this.timeOfDay;
    }
    
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public Boolean getInData() {
        return inData;
    }
    public void setInData(Boolean inData) {
        this.inData = inData;
    }
    public CurrPrevNumericValue getTodayNewUsers() {
        return todayNewUsers;
    }
    public void setTodayNewUsers(CurrPrevNumericValue todayNewUsers) {
        this.todayNewUsers = todayNewUsers;
    }
    public CurrPrevNumericValue getTodayActiveUsers() {
        return todayActiveUsers;
    }
    public void setTodayActiveUsers(CurrPrevNumericValue todayActiveUsers) {
        this.todayActiveUsers = todayActiveUsers;
    }
    public CurrPrevNumericValue getTodaySessions() {
        return todaySessions;
    }
    public void setTodaySessions(CurrPrevNumericValue todaySessions) {
        this.todaySessions = todaySessions;
    }
    public CurrPrevNumericValue getTodaySessionLength() {
        return todaySessionLength;
    }
    public void setTodaySessionLength(CurrPrevNumericValue todaySessionLength) {
        this.todaySessionLength = todaySessionLength;
    }
    public CurrPrevNumericValue getTodayPageViews() {
        return todayPageViews;
    }
    public void setTodayPageViews(CurrPrevNumericValue todayPageViews) {
        this.todayPageViews = todayPageViews;
    }
    public boolean isCompare() {
        return compare;
    }
    public void setCompare(boolean compare) {
        this.compare = compare;
    }
    public CurrPrevNumericValue getNewUsers() {
        return newUsers;
    }
    public void setNewUsers(CurrPrevNumericValue newUsers) {
        this.newUsers = newUsers;
    }
    public CurrPrevNumericValue getActiveUsers() {
        return activeUsers;
    }
    public void setActiveUsers(CurrPrevNumericValue activeUsers) {
        this.activeUsers = activeUsers;
    }
    public CurrPrevNumericValue getSessions() {
        return sessions;
    }
    public void setSessions(CurrPrevNumericValue sessions) {
        this.sessions = sessions;
    }
    public CurrPrevNumericValue getSessionLength() {
        return sessionLength;
    }
    public void setSessionLength(CurrPrevNumericValue sessionLength) {
        this.sessionLength = sessionLength;
    }
    public CurrPrevNumericValue getPageViews() {
        return pageViews;
    }
    public void setPageViews(CurrPrevNumericValue pageViews) {
        this.pageViews = pageViews;
    }
    public List<Map<String, BigDecimal>> getTopContries() {
        return topContries;
    }
    public void setTopContries(List<Map<String, BigDecimal>> topContries) {
        this.topContries = topContries;
    }
    public CurrPrevTextValue getTopResolution() {
        return topResolution;
    }
    public void setTopResolution(CurrPrevTextValue topResolution) {
        this.topResolution = topResolution;
    }
    public CurrPrevTextValue getTopAppVersion() {
        return topAppVersion;
    }
    public void setTopAppVersion(CurrPrevTextValue topAppVersion) {
        this.topAppVersion = topAppVersion;
    }
    public CurrPrevTextValue getTopOsVersion() {
        return topOsVersion;
    }
    public void setTopOsVersion(CurrPrevTextValue topOsVersion) {
        this.topOsVersion = topOsVersion;
    }
    public ComponentsInfo getComponentsInfo() {
        return componentsInfo;
    }
    public void setComponentsInfo(ComponentsInfo componentsInfo) {
        this.componentsInfo = componentsInfo;
    }
    public List<ComponentsGroup> getComponentGrpList() {
        return componentGrpList;
    }
    public void setComponentGrpList(List<ComponentsGroup> componentGrpList) {
        this.componentGrpList = componentGrpList;
    }
    public String getThisWeek() {
        return thisWeek;
    }
    public void setThisWeek(String thisWeek) {
        this.thisWeek = thisWeek;
    }
    public String getTodayNowTime() {
        return todayNowTime;
    }
    public void setTodayNowTime(String todayNowTime) {
        this.todayNowTime = todayNowTime;
    }
    public String getTodayPrevTime() {
        return todayPrevTime;
    }
    public void setTodayPrevTime(String todayPrevTime) {
        this.todayPrevTime = todayPrevTime;
    }
    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    }
    public String getYesterday() {
        return yesterday;
    }
    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
