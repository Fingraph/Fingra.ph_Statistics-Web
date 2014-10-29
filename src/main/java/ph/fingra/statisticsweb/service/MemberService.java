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

package ph.fingra.statisticsweb.service;

import java.util.List;

import ph.fingra.statisticsweb.domain.Member;

public interface MemberService {
    
	public Member get(int memberid);
	
    public Member get(String email);
    
    public void create(Member member);
    
    public void update(Member member);
    
    public void updateByAdmin(Member member);
    
    public void updateStatus(Member member);
    
    public void updateJoinstatus(Member member);
    
    public void updateMemberLastLoginTime(Member member);
    
    public boolean duplicateEmailCheck(String email);

    public void resetPassword(String email);
    
    public List<Member> getList();
    
}
