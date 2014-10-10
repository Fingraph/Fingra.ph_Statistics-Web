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

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Member extends BaseDomain {
    
    private static final long serialVersionUID = -7576987369322031199L;
    
    private Integer memberid;
    private String email;
    private String name;
    private String password;
    private String department;
    private String phone;
    private Integer status;
    private Integer joinstatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogin;
    private Integer role;
    
    public Integer getMemberid() {
        return memberid;
    }
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getJoinstatus() {
        return joinstatus;
    }
    public void setJoinstatus(Integer joinstatus) {
        this.joinstatus = joinstatus;
    }
    public Date getLastlogin() {
        return lastlogin;
    }
    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }
}
