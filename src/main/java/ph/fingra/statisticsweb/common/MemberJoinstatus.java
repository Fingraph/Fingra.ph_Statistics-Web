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

package ph.fingra.statisticsweb.common;

public enum MemberJoinstatus {
    
    WAIT(0), APPROVAL(1), REFUSE(9);
    
    private final int value;
    
    MemberJoinstatus(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static MemberJoinstatus valueOf(int value) {
        switch (value) {
        case 0:
            return WAIT;
        case 1:
            return APPROVAL;
        case 9:
            return REFUSE;
        default:
            throw new AssertionError("Unknown MemberJoinstatus : " + value);
        }
    }
}
