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

public class ComponentsInfo extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -3345345356772402958L;
    
    private List<Components> newUsersList;
    private List<Components> activeUsersList;
    private List<Components> pageViewsList;
    private List<Components> timeOfDayList;
    private List<Components> topCountriesList;
    
    public List<Components> getNewUsersList() {
        return newUsersList;
    }
    public void setNewUsersList(List<Components> newUsersList) {
        this.newUsersList = newUsersList;
    }
    public List<Components> getActiveUsersList() {
        return activeUsersList;
    }
    public void setActiveUsersList(List<Components> activeUsersList) {
        this.activeUsersList = activeUsersList;
    }
    public List<Components> getPageViewsList() {
        return pageViewsList;
    }
    public void setPageViewsList(List<Components> pageViewsList) {
        this.pageViewsList = pageViewsList;
    }
    public List<Components> getTimeOfDayList() {
        return timeOfDayList;
    }
    public void setTimeOfDayList(List<Components> timeOfDayList) {
        this.timeOfDayList = timeOfDayList;
    }
    public List<Components> getTopCountriesList() {
        return topCountriesList;
    }
    public void setTopCountriesList(List<Components> topCountriesList) {
        this.topCountriesList = topCountriesList;
    }
	
}
