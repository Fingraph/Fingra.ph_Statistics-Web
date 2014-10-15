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
	private String appkey;				//��Ű
	private String eventkey;			//�̺�ƮŰ
	private int groupkey;				//�׷�Ű
	private String name;
	private String shortName;

	private int zero_session;			//0�� ���Ǽ�
	private int one_session;			//1�� ���Ǽ�
	private int two_session;			//2�� ���Ǽ�
	private int three_session;			//3�� ���Ǽ�
	private int four_session;			//4�� ���Ǽ�
	private int five_session;			//5�� ���Ǽ�
	private int six_session;			//6�� ���Ǽ�
	private int seven_session;			//7�� ���Ǽ�
	private int eight_session;			//8�� ���Ǽ�
	private int nine_session;			//9�� ���Ǽ�
	private int ten_session;			//10�� ���Ǽ�
	private int eleven_session;			//11�� ���Ǽ�
	private int twelve_session;			//12�� ���Ǽ�
	private int thirteen_session;		//13�� ���Ǽ�
	private int fourteen_session;		//14�� ���Ǽ�
	private int fifteen_session;		//15�� ���Ǽ�
	private int sixteen_session;		//16�� ���Ǽ�
	private int seventeen_session;		//17�� ���Ǽ�
	private int eighteen_session;		//18�� ���Ǽ�
	private int nineteen_session;		//19�� ���Ǽ�
	private int twenty_session;			//20�� ���Ǽ�
	private int twentyone_session;		//21�� ���Ǽ�
	private int twentytwo_session;		//22�� ���Ǽ�
	private int twentythree_session;	//23�� ���Ǽ�

	private int total;

	//bar chart���� ������� ���ϱ����� ����
	public void calculateTotal(){
		this.total = this.zero_session + this.one_session + this.two_session + this.three_session + this.four_session + this.five_session
				     + this.six_session + this.seven_session + this.eight_session + this.nine_session + this.ten_session
				     + this.eleven_session + this.twelve_session + this.thirteen_session + this.fourteen_session + this.fifteen_session
				     + this.sixteen_session + this.seventeen_session + this.eighteen_session + this.nineteen_session + this.twenty_session
				     + this.twentyone_session + this.twentytwo_session + this.twentythree_session;
	}
}
