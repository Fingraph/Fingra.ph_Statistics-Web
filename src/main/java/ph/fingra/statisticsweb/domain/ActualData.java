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

public class ActualData extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = -5885688520885444699L;
    
    private String componentkey;
    private String componentname;
    private String name;
    private String shortname;
    private BigDecimal actual;
    private BigDecimal appTotal;
    private BigDecimal percentTotal;
    private BigDecimal percentAllCom;
    
    public String getComponentkey() {
        return componentkey;
    }
    public void setComponentkey(String componentkey) {
        this.componentkey = componentkey;
    }
    public String getComponentname() {
        return componentname;
    }
    public void setComponentname(String componentname) {
        this.componentname = componentname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortname() {
        return shortname;
    }
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
    public BigDecimal getActual() {
        return actual;
    }
    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }
    public BigDecimal getAppTotal() {
        return appTotal;
    }
    public void setAppTotal(BigDecimal appTotal) {
        this.appTotal = appTotal;
    }
    public BigDecimal getPercentTotal() {
        return percentTotal;
    }
    public void setPercentTotal(BigDecimal percentTotal) {
        this.percentTotal = percentTotal;
    }
    public BigDecimal getPercentAllCom() {
        return percentAllCom;
    }
    public void setPercentAllCom(BigDecimal percentAllCom) {
        this.percentAllCom = percentAllCom;
    }
}
