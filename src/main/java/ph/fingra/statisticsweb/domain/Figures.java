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
 * Important Figure Area 에 표시될 중요지표들.
 */
public class Figures implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -632871280000558038L;
	private String logStartDate;		//화면표시용 로그시작일
	private String logEndDate;			//화면표시용 로그종료일

	private BigDecimal total;				//전체
	private BigDecimal average;				//평균
	private BigDecimal maximum;				//최대값
	private BigDecimal minimum;				//최소값
	private BigDecimal stddev;				//표준편차
	private BigDecimal median;				//중앙값

	private String strTotal;				//전체(figure  1000->1k형식 변환)
	private String strAverage;				//평균(figure  1000->1k형식 변환)
	private String strMaximum;				//최대값(figure  1000->1k형식 변환)
	private String strMinimum;				//최소값(figure  1000->1k형식 변환)
	private String strStddev;				//표준편차(figure  1000->1k형식 변환)
	private String strMedian;				//표준편차(figure  1000->1k형식 변환)

	private BigDecimal session;				//총세션수

	private String most;					//가장많은
	private String least;					//가장적은

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
