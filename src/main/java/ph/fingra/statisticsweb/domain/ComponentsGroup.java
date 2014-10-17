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
import java.util.Date;

public class ComponentsGroup extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 2556277603499407638L;
    
    private String appkey;
    private int groupkey;
    private String groupname;
    private String shortname;
    private String description;
    private int isdel;
    private Date regdate;
    private Date moddate;
    
    public ComponentsGroup(){};
    
    public ComponentsGroup(String appkey, int groupkey, String groupname, String description, int isdel) {
        super();
        this.appkey = appkey;
        this.groupkey = groupkey;
        this.groupname = groupname;
        this.description = description;
        this.isdel = isdel;
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
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public String getShortname() {
        return shortname;
    }
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getIsdel() {
        return isdel;
    }
    public void setIsdel(int isdel) {
        this.isdel = isdel;
    }
    public Date getRegdate() {
        return regdate;
    }
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
    public Date getModdate() {
        return moddate;
    }
    public void setModdate(Date moddate) {
        this.moddate = moddate;
    }
}
