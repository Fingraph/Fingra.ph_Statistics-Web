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
import java.math.MathContext;

import ph.fingra.statisticsweb.common.util.NumberFormatUtil;

public class CurrPrevNumericValue implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 8862634182822303772L;
	static final BigDecimal cent = new BigDecimal(100);
	static final BigDecimal step1 = new BigDecimal(20);
	static final BigDecimal step2 = new BigDecimal(40);
	static final BigDecimal step3 = new BigDecimal(60);
	static final BigDecimal step4 = new BigDecimal(80);
	static final BigDecimal step5 = new BigDecimal(101);
	//주평균을구하기위해 나누어줄수 7일
	static final BigDecimal weekday = new BigDecimal(7);

	private BigDecimal current;
    private BigDecimal previous;
    private BigDecimal aux;
    private BigDecimal today;

    public boolean isRising(){
    	return current.subtract(previous).compareTo(BigDecimal.ZERO) > 0;
    }

    //이번주평균
    public String getStrWeekAvg(){
    	return NumberFormatUtil.shortScaleConvertWithBigDecimal(current.divide(weekday,MathContext.DECIMAL32));
	}

    public String getSign(){
    	int result = current.subtract(previous).setScale(1, BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO);

    	String sign = "";
    	if(result>0){
    		sign="+";
    	}else if(result==0){
    		sign="";
    	}else if(result<0){
    		sign="-";
    	}
    	return sign;
        }

    public boolean isHasPrevious(){
	return (previous.intValue() != 0);
    }

    public BigDecimal getIncrement() {
	return current.subtract(previous).abs();
    }

    public BigDecimal getGrowthRate() {
	if(previous.doubleValue() == 0.00)
	    return BigDecimal.ZERO;
	return current.subtract(previous).divide(previous,MathContext.DECIMAL32).multiply(cent).abs();
    }

    public String getBgClass(){
    	String rising="up";
    	String step="";
    	if(isRising()){
    		rising="up";
    	}else{
    		rising="down";
    	}

    	if(getGrowthRate().compareTo(BigDecimal.ZERO) >=0 && getGrowthRate().compareTo(step1) == -1){
    		step="1";
    	}else if(getGrowthRate().compareTo(step1) >=0 && getGrowthRate().compareTo(step2) == -1){
    		step="2";
    	}else if(getGrowthRate().compareTo(step2) >=0 && getGrowthRate().compareTo(step3) == -1){
    		step="2";
    	}else if(getGrowthRate().compareTo(step3) >=0 && getGrowthRate().compareTo(step4) == -1){
    		step="2";
    	}else if(getGrowthRate().compareTo(step4) >=0 && getGrowthRate().compareTo(step5) == -1){
    		step="2";
    	}else{
    		step="2";
    	}
    	return rising+step;
    }
    
    public String getStrToday() {
    	return NumberFormatUtil.shortScaleConvertWithBigDecimal(today);
	}
    public String getStrCurrent() {
    	return NumberFormatUtil.shortScaleConvertWithBigDecimal(current);
	}



    public static void main(String [] args){
	BigDecimal a = new BigDecimal(2413.7143); // current
	BigDecimal b = new BigDecimal(0.4286); // previous
	System.out.println(a.subtract(b));
	System.out.println(a.subtract(b).divide(b,MathContext.DECIMAL32).multiply(cent).abs());
    }

}
