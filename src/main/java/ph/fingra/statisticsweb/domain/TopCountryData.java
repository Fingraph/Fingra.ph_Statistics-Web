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
import java.util.List;

public class TopCountryData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -1025941185605444322L;
    
    private List<CountryData> sumList;
    private List<CountryTimeSeriesData> ctsList;
    private List<String> topNList;
    
    public List<CountryData> getSumList() {
        return sumList;
    }
    public void setSumList(List<CountryData> sumList) {
        this.sumList = sumList;
    }
    public List<CountryTimeSeriesData> getCtsList() {
        return ctsList;
    }
    public void setCtsList(List<CountryTimeSeriesData> ctsList) {
        this.ctsList = ctsList;
    }
    public List<String> getTopNList() {
        return topNList;
    }
    public void setTopNList(List<String> topNList) {
        this.topNList = topNList;
    }
}
