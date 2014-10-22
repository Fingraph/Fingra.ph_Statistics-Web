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

import java.util.List;

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
    public void insert(App app) {
        sqlSessionTemplate.insert("app.insert", app);
    }
    
    @Override
    public void update(App app) {
        sqlSessionTemplate.update("app.update", app);
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
    public void updateAppInfo(AppInfo appInfo) {
        sqlSessionTemplate.update("app.updateAppInfo", appInfo);
    }
    
    @Override
    public List<AppCategory> findAllCategories() {
        return sqlSessionTemplate.selectList("app.findAllCategories");
    }
    
    @Override
    public List<App> getAppList(DashBoardSearchParam param) {
        return sqlSessionTemplate.selectList("app.getAppList", param);
    }
    
    @Override
    public App findById(String appkey) {
        return sqlSessionTemplate.selectOne("app.findById", appkey);
    }
}
