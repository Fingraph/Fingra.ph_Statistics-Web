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

public class CurrNumericValue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793086869318199937L;
	static final BigDecimal cent = new BigDecimal(100);
	static final BigDecimal error_reference = new BigDecimal("20");
	/*
	 * ex) error를구할때
	 *     numerator: invalid
	 *     denominator: correct
	 *
	 */
    private BigDecimal numerator;//분자
    private BigDecimal denominator;//분모
    private String name; //eventName or traceName ...
    private String nameKey;//eventkey or traceKey or countrycode


    //비율
    public BigDecimal getRate() {
	if(numerator.intValue() == 0)
	    return BigDecimal.ZERO;
	return numerator.divide(denominator,MathContext.DECIMAL32).multiply(cent);
    }
    //표시여부
    public boolean isDisplay(){
    	return getRate().compareTo(error_reference) > 0?true:false;
    }

    public static void main(String [] args){
	BigDecimal a = new BigDecimal(1425);
	BigDecimal b = new BigDecimal(7051);
	System.out.println(a.subtract(b));
	System.out.println(a.divide(b,MathContext.DECIMAL32).multiply(cent));
	System.out.println(a.divide(b,MathContext.DECIMAL32).multiply(cent).compareTo(error_reference) > 0?true:false);

    }

}
