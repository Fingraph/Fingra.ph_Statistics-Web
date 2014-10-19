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
import java.util.List;
import java.util.Map;

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.AppCategory;
import ph.fingra.statisticsweb.domain.AppInfo;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;

public interface AppDao {
    
    List<AppCategory> findAllCategories();
    
    void insert(App app);
    
    List<App> findPairedAppsByMemberid(Integer memberid);
    
    List<App> findNotPairedAppsByMemberid(Integer memberid);
    
    List<App> getAppList(DashBoardSearchParam param);
    
    void updateAppInfo(AppInfo appInfo);
    
    void updateAppType(App app);
    
    void updateAppStatus(App app);
    
    Map<String, BigDecimal> countAppTypeByMemberid(Integer memberid);
    
    App findById(String appkey);
    
    void update(App app);
    
    List<App> getAppListByReSeller(DashBoardSearchParam param);
    
    int hasAccessPermission(String email, String appkey);
}
