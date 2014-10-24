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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ph.fingra.statisticsweb.common.MemberJoinstatus;
import ph.fingra.statisticsweb.common.MemberStatus;
import ph.fingra.statisticsweb.dao.MemberDao;
import ph.fingra.statisticsweb.domain.Member;
import ph.fingra.statisticsweb.security.FingraphUser;

@Service
public class MemberServiceImpl implements MemberService {
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Member get(int memberid) {
    	return memberDao.findById(memberid);
    }
    
    public Member get(String userid) {
        return memberDao.findByUserid(userid);
    }
    
    public void create(Member member) {
    	member.setPassword(passwordEncoder.encode(member.getPassword()));
    	member.setStatus(MemberStatus.ACTIVE.getValue());
    	member.setJoinstatus(MemberJoinstatus.WAIT.getValue());
    	memberDao.insert(member);
    }
    
    public void update(Member member) {
    	if (!StringUtils.isEmpty(member.getPassword())) {
    		member.setPassword(passwordEncoder.encode(member.getPassword()));
    	}
    	memberDao.update(member);
    	UserDetails newPrincipal = new FingraphUser(get(member.getMemberid()));
    	Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
    	UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, 
    																						currentAuth.getCredentials(), 
    																						newPrincipal.getAuthorities());
    	newAuth.setDetails(currentAuth.getDetails());
    	SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
    
    public void updateMemberLastLoginTime(Member member) {
        memberDao.updateMemberLastLoginTime(member);
    }
    
    public boolean duplicateUseridCheck(String userid) {
    	return memberDao.countByUserid(userid) == 0? false : true;
    }
    
    public void resetPassword(String userid) {
    	Member member = get(userid);
    	member.setStatus(MemberStatus.RESET_REQUESTED.getValue());
    	memberDao.updateStatus(member);
    }
    
    @Override
    public List<Member> getList() {
        return memberDao.getList();
    }
    
}
