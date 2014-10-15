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

public class FrequencyData implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -5838790327088381375L;
	private String year;			//연도
	private String month;			//월
	private String day;				//일
	private String appkey;			//앱키
	private String fromDate;
	private String toDate;

	private String date;			//통계일
	private int freq_user_1;		//1회실행 사용자수
	private int freq_user_2;		//2회실행 사용자수
	private int freq_user_3_4;		//3회~4회실행 사용자수
	private int freq_user_5_6;		//5회~6회실행 사용자수
	private int freq_user_7_9;		//7회~9회실행 사용자수
	private int freq_user_10_14;	//10회~14회실행 사용자수
	private int freq_user_15_19;	//15회~19회실행 사용자수
	private int freq_user_20_49;	//20회~49회실행 사용자수
	private int freq_user_50_99;	//50회~99회실행 사용자수
	private int freq_user_100_499;	//100회~499회실행 사용자수
	private int freq_user_over_500;	//500회이상실행 사용자수
	private int total;

	//bar chart에서 백분율을 구하기위한 총합
	public void calculateTotal(){
		this.total = this.freq_user_1 + this.freq_user_2 + this.freq_user_3_4 + this.freq_user_5_6 + this.freq_user_7_9 + this.freq_user_10_14
				     + this.freq_user_15_19 + this.freq_user_20_49 + this.freq_user_50_99 + this.freq_user_100_499 + this.freq_user_over_500;
	}
}
