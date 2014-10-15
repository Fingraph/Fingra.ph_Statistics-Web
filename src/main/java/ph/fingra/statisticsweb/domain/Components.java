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

public class Components implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 2917076540148471576L;
	private String appkey;
	private String eventkey;
	private String eventName;
	private String eventShortName;

	private String name;
	private String shortName;
	private int groupkey;
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
}
