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

import ph.fingra.statisticsweb.domain.App;
import ph.fingra.statisticsweb.domain.ComponentsInfo;
import ph.fingra.statisticsweb.domain.DashBoardSearchParam;

public interface DashBoardService {

    App getApp(DashBoardSearchParam param);

	App getDashBoardAppInfo(DashBoardSearchParam param);

	App getPerformanceSectionInfo(DashBoardSearchParam param);

	App getDistributionSectionInfo(DashBoardSearchParam param);

	App getComponentsSectionInfo(DashBoardSearchParam param);

	List<App> getAppList(DashBoardSearchParam param);
	
	ComponentsInfo getComponetsSnapshotAjax(DashBoardSearchParam param);

	App getTodaySectionInfo(DashBoardSearchParam param);

}
