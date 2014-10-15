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
import java.util.List;
import java.util.Map;

public class DashBoard implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 8892883125046436502L;
	private String appkey;
	private CurrNumericValue logs;
	private Boolean inData;
	private CurrPrevNumericValue todayNewUsers;
	private CurrPrevNumericValue todayActiveUsers;
	private CurrPrevNumericValue todaySessions;//session
	private CurrPrevNumericValue todaySessionLength;//sessionLength
	private CurrPrevNumericValue todayPageViews;

	private boolean compare;
	private CurrPrevNumericValue newUsers;
	private CurrPrevNumericValue activeUsers;
	private CurrPrevNumericValue sessions;//session
	private CurrPrevNumericValue sessionLength;//sessionLength
	private CurrPrevNumericValue pageViews;

	private String dayOfWeek;
	private String timeOfDay;
	private List<Map<String,BigDecimal>> topContries;

	private CurrPrevTextValue topResolution;
	private CurrPrevTextValue topAppVersion;
	private CurrPrevTextValue topOsVersion;

	private ComponentsInfo componentsInfo;
	private List<ComponentsGroup> componentGrpList;

	//PERFORMANCE SNAPSHOT thisWeek
	private String thisWeek;

	//TODAY SNAPSHOT todayTime
	private String todayNowTime;
	private String todayPrevTime;
	private String today;
	private String yesterday;
	
	public String getDayOfWeek(){
		return this.dayOfWeek==null?"N/A":this.dayOfWeek;
	}

	public String getTimeOfDay(){
		return this.timeOfDay==null?"N/A":this.timeOfDay;
	}

}
