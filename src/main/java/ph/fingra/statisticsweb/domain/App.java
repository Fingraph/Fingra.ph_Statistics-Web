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

import ph.fingra.statisticsweb.common.AppPlatform;

public class App extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 6282228105674613858L;
    
    private String appkey;
    private Integer platform;
    private String appname;
    private String appid;
    private String marketid;
    private Integer categoryid;
    private int status;
    private int apptype;
    
    private AppInfo appInfo;
    private DashBoard dashBoard;
    
    public boolean hasValidAppId() {
    	if (this.appid == null || this.appid.trim().length() < 1)
    	    return false;
    	if (AppPlatform.valueOf(platform) == AppPlatform.IPHONE
    	        && appid.matches("\\d{9}"))
    	    return true;
    	if (AppPlatform.valueOf(platform) == AppPlatform.ANDROID
    	        && appid.matches("^([a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*)$"))
    	    return true;
    	return false;
    }
    
    public String getAppkey() {
        return appkey;
    }
    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public Integer getPlatform() {
        return platform;
    }
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    public String getAppname() {
        return appname;
    }
    public void setAppname(String appname) {
        this.appname = appname;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getMarketid() {
        return marketid;
    }
    public void setMarketid(String marketid) {
        this.marketid = marketid;
    }
    public Integer getCategoryid() {
        return categoryid;
    }
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getApptype() {
        return apptype;
    }
    public void setApptype(int apptype) {
        this.apptype = apptype;
    }
    public AppInfo getAppInfo() {
        return appInfo;
    }
    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }
    public DashBoard getDashBoard() {
        return dashBoard;
    }
    public void setDashBoard(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
    }
}
