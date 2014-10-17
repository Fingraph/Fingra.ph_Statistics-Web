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

public class TopResolutionData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 3516544104474214231L;
    
    private List<ResolutionData> sumList;
    private List<ResolutionTimeSeriesData> rtsList;
    
    public List<ResolutionData> getSumList() {
        return sumList;
    }
    public void setSumList(List<ResolutionData> sumList) {
        this.sumList = sumList;
    }
    public List<ResolutionTimeSeriesData> getRtsList() {
        return rtsList;
    }
    public void setRtsList(List<ResolutionTimeSeriesData> rtsList) {
        this.rtsList = rtsList;
    }
}
