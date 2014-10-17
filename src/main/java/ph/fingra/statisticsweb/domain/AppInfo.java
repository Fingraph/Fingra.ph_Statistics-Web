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

public class AppInfo extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 2945946960612023124L;
    
    private String appkey;
    private String smallicon;
    private String mediumicon;
    private String largeicon;
    
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getSmallicon() {
        return smallicon;
    }
    public void setSmallicon(String smallicon) {
        this.smallicon = smallicon;
    }
    public String getMediumicon() {
        return mediumicon;
    }
    public void setMediumicon(String mediumicon) {
        this.mediumicon = mediumicon;
    }
    public String getLargeicon() {
        return largeicon;
    }
    public void setLargeicon(String largeicon) {
        this.largeicon = largeicon;
    }
}
