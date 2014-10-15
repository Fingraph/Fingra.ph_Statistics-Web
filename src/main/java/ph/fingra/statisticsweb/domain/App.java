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

public class App extends BaseDomain {
    private static final long serialVersionUID = 6282228105674613858L;
    private String appkey;
    private Integer platform;
    private String appName;
    private String appId;
    private String marketId;
    private Integer categoryId;
    private int status;
    private int appType;
    
    private AppInfo appInfo;
    private DashBoard dashBoard;

    public boolean hasValidAppId() {
	if (this.appId == null || this.appId.trim().length() < 1)
	    return false;
	if (AppPlatform.valueOf(platform) == AppPlatform.IPHONE && appId.matches("\\d{9}"))
	    return true;
	if (AppPlatform.valueOf(platform) == AppPlatform.ANDROID && appId.matches("^([a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)*)$"))
	    return true;
	return false;
    }
}
