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

package ph.fingra.statisticsweb.exception;

public class UnauthorizedAccessException extends RuntimeException {
    
    private static final long serialVersionUID = -2015330028919975348L;
    
    private Object additionalInfo;
    
    public UnauthorizedAccessException(Throwable e) {
        super(e);
    }
    
    public UnauthorizedAccessException(String msg, Throwable e) {
        super(msg, e);
    }
    
    public UnauthorizedAccessException(String msg) {
        super(msg);
    }
    
    public UnauthorizedAccessException(String msg, Object additionalInfo) {
        super(msg);
        this.additionalInfo = additionalInfo;
    }
    
    public Object getAdditionalInfo() {
        return additionalInfo;
    }
    
    public void setAdditionalInfo(Object additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
