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
import java.util.List;

public class ComponentsManage extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 8328412358449399229L;
    
    private Integer groupCount;
    private Integer componentCount;
    
    private String appkey;
    private Integer groupkey;
    private String groupname;
    private String groupShortname;
    private String description;
    private Date regdate;
    private List<Component> clist;
    
    public Integer getGroupCount() {
        return groupCount;
    }
    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }
    public Integer getComponentCount() {
        return componentCount;
    }
    public void setComponentCount(Integer componentCount) {
        this.componentCount = componentCount;
    }
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public Integer getGroupkey() {
        return groupkey;
    }
    public void setGroupkey(Integer groupkey) {
        this.groupkey = groupkey;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public String getGroupShortname() {
        return groupShortname;
    }
    public void setGroupShortname(String groupShortname) {
        this.groupShortname = groupShortname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getRegdate() {
        return regdate;
    }
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
    public List<Component> getClist() {
        return clist;
    }
    public void setClist(List<Component> clist) {
        this.clist = clist;
    }
}
