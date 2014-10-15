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

public class ComponentsGroup implements Serializable{


	private static final long serialVersionUID = 2556277603499407638L;
	private String appkey;
	private int groupkey;
	private String groupName;
	private String shortName;
	private String description;
	private int isdel;
	private Date regDate;
	private Date modDate;

	public ComponentsGroup(){};

	public ComponentsGroup(String appkey, int groupkey, String groupName, String description, int isdel) {
		super();
		this.appkey = appkey;
		this.groupkey = groupkey;
		this.groupName = groupName;
		this.description = description;
		this.isdel = isdel;
	}

}
