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
	private String year;			//����
	private String month;			//��
	private String day;				//��
	private String appkey;			//��Ű
	private String fromDate;
	private String toDate;

	private String date;			//�����
	private int freq_user_1;		//1ȸ���� ����ڼ�
	private int freq_user_2;		//2ȸ���� ����ڼ�
	private int freq_user_3_4;		//3ȸ~4ȸ���� ����ڼ�
	private int freq_user_5_6;		//5ȸ~6ȸ���� ����ڼ�
	private int freq_user_7_9;		//7ȸ~9ȸ���� ����ڼ�
	private int freq_user_10_14;	//10ȸ~14ȸ���� ����ڼ�
	private int freq_user_15_19;	//15ȸ~19ȸ���� ����ڼ�
	private int freq_user_20_49;	//20ȸ~49ȸ���� ����ڼ�
	private int freq_user_50_99;	//50ȸ~99ȸ���� ����ڼ�
	private int freq_user_100_499;	//100ȸ~499ȸ���� ����ڼ�
	private int freq_user_over_500;	//500ȸ�̻���� ����ڼ�
	private int total;

	//bar chart���� ������� ���ϱ����� ����
	public void calculateTotal(){
		this.total = this.freq_user_1 + this.freq_user_2 + this.freq_user_3_4 + this.freq_user_5_6 + this.freq_user_7_9 + this.freq_user_10_14
				     + this.freq_user_15_19 + this.freq_user_20_49 + this.freq_user_50_99 + this.freq_user_100_499 + this.freq_user_over_500;
	}
}
