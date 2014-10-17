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
import java.util.List;

public class FingraphSearchParam extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -6590497897729823879L;
    
    private String appkey;
    private String from;
    private String to;
    private String fromTo;
    private String period;
    private String term;
    private String segment;
    private int topN = 5; //topN data
    List<String> topNList;
    List<Integer> topNGrpList;
    private boolean isOthers = false;
    private String selectValue; //components - timeofday, topcountries
    private Integer groupkey;
    
    //today snapshot
    private String today;
    private String nowTime;
    
    public FingraphSearchParam(){}
    
    public FingraphSearchParam(String appkey, String from, String to,
            String fromTo, String period, String term, String segment,
            int topN, List<String> topNList, List<Integer> topNGrpList,
            boolean isOthers, String selectValue, Integer groupkey,
            String today, String nowTime) {
        super();
        this.appkey = appkey;
        this.from = from;
        this.to = to;
        this.fromTo = fromTo;
        this.period = period;
        this.term = term;
        this.segment = segment;
        this.topN = topN;
        this.topNList = topNList;
        this.topNGrpList = topNGrpList;
        this.isOthers = isOthers;
        this.selectValue = selectValue;
        this.groupkey = groupkey;
        this.today = today;
        this.nowTime = nowTime;
    }
    
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getFromTo() {
        return fromTo;
    }
    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public String getSegment() {
        return segment;
    }
    public void setSegment(String segment) {
        this.segment = segment;
    }
    public int getTopN() {
        return topN;
    }
    public void setTopN(int topN) {
        this.topN = topN;
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
    public boolean isOthers() {
        return isOthers;
    }
    public void setOthers(boolean isOthers) {
        this.isOthers = isOthers;
    }
    public String getSelectValue() {
        return selectValue;
    }
    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }
    public Integer getGroupkey() {
        return groupkey;
    }
    public void setGroupkey(Integer groupkey) {
        this.groupkey = groupkey;
    }
    public String getToday() {
        return today;
    }
    public void setToday(String today) {
        this.today = today;
    }
    public String getNowTime() {
        return nowTime;
    }
    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }
}
