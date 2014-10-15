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

public class SessionLengthData implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6656569465216688390L;
	private String year;			//����
	private String month;			//��
	private String day;				//��
	private String week;
	private String appkey;			//��Ű
	private String date;
	private String fromDate;
	private String toDate;

	private int under_three_sec;	//3�ʹ̸�
	private int three_ten_sec;		//3���̻� 10�ʹ̸�
	private int ten_thirty_sec;		//10���̻� 30�ʹ̸�
	private int thirty_sixty_sec;	//30���̻� 60�ʹ̸�
	private int one_three_min;		//1���̻� 3�й̸�
	private int three_ten_min;		//3���̻� 10�й̸�
	private int ten_thirty_min;		//10���̻� 30�й̸�
	private int over_thirty_min;	//30���̻�
	private int total;

	//bar chart���� ������� ���ϱ����� ����
	public void calculateTotal(){
		this.total = this.under_three_sec + this.three_ten_sec + this.ten_thirty_sec + this.thirty_sixty_sec + this.one_three_min + this.three_ten_min
				     + this.ten_thirty_min + this.ten_thirty_min;
	}


}
