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

import java.util.List;

import ph.fingra.statisticsweb.common.util.DateTimeUtil;

public class DashBoardSearchParam  extends AbstractSearchParam {
    
    private static final long serialVersionUID = -5790470817833526178L;
    
    private String prevFrom;
    private String prevTo;
    private String period;
    
    private String yesterday;
    private String beforeYesterday;
    private String now;
    private String nowTime;
    private String prevTime;
    
    private String source;
    private int memberid;
    List<String> topNList;
    List<Integer> topNGrpList;
    private int role;
    private String menu;    //COMPONENTS intergroup compare menu
    
    private int groupkey = -2; //COMPONENTS groupkey
    
    public enum DashBoardChartSource{NEW_USER,ACTIVE_USER,SESSION,SESSION_LENGTH};
    
    public void setPeriod(String period) {
        this.period = period;
        String[] fromTo = DateTimeUtil.getDashboardFromToWithPrev(period);
        setFrom(fromTo[0]);
        setTo(fromTo[1]);
        setPrevFrom(fromTo[2]);
        setPrevTo(fromTo[3]);
        setYesterday(fromTo[4]);
        setBeforeYesterday(fromTo[5]);
        
        //today
        setNow(fromTo[6]);
        setNowTime(fromTo[7]);
        setPrevTime(fromTo[8]);
    }
    
    public DashBoardChartSource getSourceType(){
        return DashBoardChartSource.valueOf(source);
    }
    
    public String getPrevFrom() {
        return prevFrom;
    }
    public void setPrevFrom(String prevFrom) {
        this.prevFrom = prevFrom;
    }
    public String getPrevTo() {
        return prevTo;
    }
    public void setPrevTo(String prevTo) {
        this.prevTo = prevTo;
    }
    public String getYesterday() {
        return yesterday;
    }
    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }
    public String getBeforeYesterday() {
        return beforeYesterday;
    }
    public void setBeforeYesterday(String beforeYesterday) {
        this.beforeYesterday = beforeYesterday;
    }
    public String getNow() {
        return now;
    }
    public void setNow(String now) {
        this.now = now;
    }
    public String getNowTime() {
        return nowTime;
    }
    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }
    public String getPrevTime() {
        return prevTime;
    }
    public void setPrevTime(String prevTime) {
        this.prevTime = prevTime;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public int getMemberid() {
        return memberid;
    }
    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }
    public List<String> getTopNList() {
        return topNList;
    }
    public void setTopNList(List<String> topNList) {
        this.topNList = topNList;
    }
    public List<Integer> getTopNGrpList() {
        return topNGrpList;
    }
    public void setTopNGrpList(List<Integer> topNGrpList) {
        this.topNGrpList = topNGrpList;
    }
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public int getGroupkey() {
        return groupkey;
    }
    public void setGroupkey(int groupkey) {
        this.groupkey = groupkey;
    }
    public String getPeriod() {
        return period;
    }
}
