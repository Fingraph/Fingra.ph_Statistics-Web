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

import ph.fingra.statisticsweb.common.util.NumberFormatUtil;

public class Components extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 2917076540148471576L;
    
    private String componentkey;
    private String appkey;
    private int groupkey;
    private String componentname;
    private String componentShortname;
    
    private String name;
    private String shortname;
    private String key;
    private BigDecimal intValue;
    private BigDecimal value;
    private int max;
    private int total;
    private String strIntValue;
    private String  strValue;
    private String country2;
    private String country3;
    
    public void setIntValue(BigDecimal intValue) {
        this.strIntValue = NumberFormatUtil.shortScaleConvertWithBigDecimal(intValue);
        this.intValue = intValue;
    }
    
    public String getComponentkey() {
        return componentkey;
    }
    public void setComponentkey(String componentkey) {
        this.componentkey = componentkey;
    }
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public int getGroupkey() {
        return groupkey;
    }
    public void setGroupkey(int groupkey) {
        this.groupkey = groupkey;
    }
    public String getComponentname() {
        return componentname;
    }
    public void setComponentname(String componentname) {
        this.componentname = componentname;
    }
    public String getComponentShortname() {
        return componentShortname;
    }
    public void setComponentShortname(String componentShortname) {
        this.componentShortname = componentShortname;
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
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getStrIntValue() {
        return strIntValue;
    }
    public void setStrIntValue(String strIntValue) {
        this.strIntValue = strIntValue;
    }
    public String getStrValue() {
        return strValue;
    }
    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }
    public String getCountry2() {
        return country2;
    }
    public void setCountry2(String country2) {
        this.country2 = country2;
    }
    public String getCountry3() {
        return country3;
    }
    public void setCountry3(String country3) {
        this.country3 = country3;
    }
    public BigDecimal getIntValue() {
        return intValue;
    }
}
