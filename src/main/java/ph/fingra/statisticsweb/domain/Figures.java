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

public class Figures extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -632871280000558038L;
    
    private String logStartDate;    //log startdate for display
    private String logEndDate;      //log enddate for display
    
    private BigDecimal total;
    private BigDecimal average;
    private BigDecimal maximum;
    private BigDecimal minimum;
    private BigDecimal stddev;      //standard deviation
    private BigDecimal median;      //median value
    
    private String strTotal;        //format transform(1000->1k)
    private String strAverage;      //format transform
    private String strMaximum;      //format transform
    private String strMinimum;      //format transform
    private String strStddev;       //format transform
    private String strMedian;       //format transform
    
    private BigDecimal session;     //total session count
    
    private String most;
    private String least;
    
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
    
    public String getLogStartDate() {
        return logStartDate;
    }
    public void setLogStartDate(String logStartDate) {
        this.logStartDate = logStartDate;
    }
    public String getLogEndDate() {
        return logEndDate;
    }
    public void setLogEndDate(String logEndDate) {
        this.logEndDate = logEndDate;
    }
    public String getStrTotal() {
        return strTotal;
    }
    public void setStrTotal(String strTotal) {
        this.strTotal = strTotal;
    }
    public String getStrAverage() {
        return strAverage;
    }
    public void setStrAverage(String strAverage) {
        this.strAverage = strAverage;
    }
    public String getStrMaximum() {
        return strMaximum;
    }
    public void setStrMaximum(String strMaximum) {
        this.strMaximum = strMaximum;
    }
    public String getStrMinimum() {
        return strMinimum;
    }
    public void setStrMinimum(String strMinimum) {
        this.strMinimum = strMinimum;
    }
    public String getStrStddev() {
        return strStddev;
    }
    public void setStrStddev(String strStddev) {
        this.strStddev = strStddev;
    }
    public String getStrMedian() {
        return strMedian;
    }
    public void setStrMedian(String strMedian) {
        this.strMedian = strMedian;
    }
    public BigDecimal getSession() {
        return session;
    }
    public void setSession(BigDecimal session) {
        this.session = session;
    }
    public String getMost() {
        return most;
    }
    public void setMost(String most) {
        this.most = most;
    }
    public String getLeast() {
        return least;
    }
    public void setLeast(String least) {
        this.least = least;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public BigDecimal getAverage() {
        return average;
    }
    public BigDecimal getMaximum() {
        return maximum;
    }
    public BigDecimal getMinimum() {
        return minimum;
    }
    public BigDecimal getStddev() {
        return stddev;
    }
    public BigDecimal getMedian() {
        return median;
    }
}
