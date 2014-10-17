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

public class AppCategory extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 1599563035002114518L;
    
    private int categoryid;
    private String categoryname;
    private String appstorecategory;
    private String googleplaycategory;
    
    public int getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }
    public String getCategoryname() {
        return categoryname;
    }
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    public String getAppstorecategory() {
        return appstorecategory;
    }
    public void setAppstorecategory(String appstorecategory) {
        this.appstorecategory = appstorecategory;
    }
    public String getGoogleplaycategory() {
        return googleplaycategory;
    }
    public void setGoogleplaycategory(String googleplaycategory) {
        this.googleplaycategory = googleplaycategory;
    }
}
