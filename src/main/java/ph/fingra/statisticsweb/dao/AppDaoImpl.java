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

package ph.fingra.statisticsweb.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.AppCategory;
import ph.fingra.statisticsweb.domain.AppInfo;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;

@Repository
public class AppDaoImpl implements AppDao {
    
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    @Override
    public List<AppCategory> findAllCategories() {
        return sqlSessionTemplate.selectList("app.findAllCategories");
    }
    
    @Override
    public void insert(App app) {
        sqlSessionTemplate.insert("app.insert", app);
    }
    
    @Override
    public List<App> findPairedAppsByMemberid(Integer memberid) {
        return sqlSessionTemplate.selectList("app.findPairedAppsByMemberid", memberid);
    }
    
    @Override
    public List<App> findNotPairedAppsByMemberid(Integer memberid) {
        return sqlSessionTemplate.selectList("app.findNotPairedAppsByMemberid", memberid);
    }
    
    @Override
    public List<App> getAppList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("app.getAppList", param);
    }
    
    @Override
    public void updateAppInfo(AppInfo appInfo) {
        sqlSessionTemplate.update("app.updateAppInfo", appInfo);
    }
    
    @Override
    public void updateAppType(App app) {
        sqlSessionTemplate.update("app.updateAppType", app);
    }
    
    @Override
    public void updateAppStatus(App app) {
        sqlSessionTemplate.update("app.updateAppStatus", app);
    }
    
    @Override
    public Map<String, BigDecimal> countAppTypeByMemberid(Integer memberid) {
        return sqlSessionTemplate.selectOne("app.countAppTypeByMemberid", memberid);
    }
    
    @Override
    public App findById(String appkey) {
        return sqlSessionTemplate.selectOne("app.findById", appkey);
    }
    
    @Override
    public void update(App app) {
        sqlSessionTemplate.update("app.update", app);
    }
    
    @Override
    public List<App> getAppListByReSeller(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("app.getAppListByReSeller", param);
    }
    
    @Override
    public int hasAccessPermission(String email, String appkey){
        Map<String, String> map = new HashMap<String, String>();
        map.put("email",email);
        map.put("appkey",appkey);
        return sqlSessionTemplate.selectOne("app.hasAccessPermission",map);
    }
}
