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

public enum MemberRole {
    
    ROLE_USER(0), ROLE_ADMIN(9);
    
    private final int value;
    
    MemberRole(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static MemberRole valueOf(int value) {
        switch (value) {
        case 0:
            return ROLE_USER;
        case 9:
            return ROLE_ADMIN;
        default:
            throw new AssertionError("Unknown MemberRole : " + value);
        }
    }
}
