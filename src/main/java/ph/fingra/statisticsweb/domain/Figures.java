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
import java.text.DateFormatSymbols;
import java.util.Locale;

import ph.fingra.statisticsweb.common.util.NumberFormatUtil;

/**
 * @author parkhs93
 * Important Figure Area �� ǥ�õ� �߿���ǥ��.
 */
public class Figures implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -632871280000558038L;
	private String logStartDate;		//ȭ��ǥ�ÿ� �α׽�����
	private String logEndDate;			//ȭ��ǥ�ÿ� �α�������

	private BigDecimal total;				//��ü
	private BigDecimal average;				//���
	private BigDecimal maximum;				//�ִ밪
	private BigDecimal minimum;				//�ּҰ�
	private BigDecimal stddev;				//ǥ������
	private BigDecimal median;				//�߾Ӱ�

	private String strTotal;				//��ü(figure  1000->1k���� ��ȯ)
	private String strAverage;				//���(figure  1000->1k���� ��ȯ)
	private String strMaximum;				//�ִ밪(figure  1000->1k���� ��ȯ)
	private String strMinimum;				//�ּҰ�(figure  1000->1k���� ��ȯ)
	private String strStddev;				//ǥ������(figure  1000->1k���� ��ȯ)
	private String strMedian;				//ǥ������(figure  1000->1k���� ��ȯ)

	private BigDecimal session;				//�Ѽ��Ǽ�

	private String most;					//���帹��
	private String least;					//��������

	public void setTotal(BigDecimal total) {
		this.strTotal = NumberFormatUtil.shortScaleConvertWithBigDecimal(total);
		this.total = total;
	}

	public void setAverage(BigDecimal average) {
		this.strAverage = NumberFormatUtil.shortScaleConvertWithBigDecimal(average);
		this.average=average;
	}

	public void setMaximum(BigDecimal maximum) {
		this.strMaximum = NumberFormatUtil.shortScaleConvertWithBigDecimal(maximum);
		this.maximum = maximum;
	}

	public void setMinimum(BigDecimal minimum) {
		this.strMinimum = NumberFormatUtil.shortScaleConvertWithBigDecimal(minimum);
		this.minimum = minimum;
	}

	public void setStddev(BigDecimal stddev) {
		this.strStddev = NumberFormatUtil.shortScaleConvertWithBigDecimal(stddev);
		this.stddev = stddev;
	}
	
	public void setMedian(BigDecimal median) {
		this.strMedian = NumberFormatUtil.shortScaleConvertWithBigDecimal(median);
		this.median = median;
	}

}
