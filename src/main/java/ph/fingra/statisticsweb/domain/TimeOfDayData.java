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

public class TimeOfDayData implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -2660433755063566539L;
	private String appkey;				//앱키
	private String eventkey;			//이벤트키
	private int groupkey;				//그룹키
	private String name;
	private String shortName;

	private int zero_session;			//0시 세션수
	private int one_session;			//1시 세션수
	private int two_session;			//2시 세션수
	private int three_session;			//3시 세션수
	private int four_session;			//4시 세션수
	private int five_session;			//5시 세션수
	private int six_session;			//6시 세션수
	private int seven_session;			//7시 세션수
	private int eight_session;			//8시 세션수
	private int nine_session;			//9시 세션수
	private int ten_session;			//10시 세션수
	private int eleven_session;			//11시 세션수
	private int twelve_session;			//12시 세션수
	private int thirteen_session;		//13시 세션수
	private int fourteen_session;		//14시 세션수
	private int fifteen_session;		//15시 세션수
	private int sixteen_session;		//16시 세션수
	private int seventeen_session;		//17시 세션수
	private int eighteen_session;		//18시 세션수
	private int nineteen_session;		//19시 세션수
	private int twenty_session;			//20시 세션수
	private int twentyone_session;		//21시 세션수
	private int twentytwo_session;		//22시 세션수
	private int twentythree_session;	//23시 세션수

	private int total;

	//bar chart에서 백분율을 구하기위한 총합
	public void calculateTotal(){
		this.total = this.zero_session + this.one_session + this.two_session + this.three_session + this.four_session + this.five_session
				     + this.six_session + this.seven_session + this.eight_session + this.nine_session + this.ten_session
				     + this.eleven_session + this.twelve_session + this.thirteen_session + this.fourteen_session + this.fifteen_session
				     + this.sixteen_session + this.seventeen_session + this.eighteen_session + this.nineteen_session + this.twenty_session
				     + this.twentyone_session + this.twentytwo_session + this.twentythree_session;
	}
}
