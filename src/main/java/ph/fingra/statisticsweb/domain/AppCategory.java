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

import java.io.Serializable;

public class AppCategory implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1599563035002114518L;
	private int categoryId;
    private String categoryName;
    private String appStoreCategory;
    private String goolePlayCategory;
    
    public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getGoolePlayCategory() {
		return goolePlayCategory;
	}
	public void setGoolePlayCategory(String goolePlayCategory) {
		this.goolePlayCategory = goolePlayCategory;
	}
	public String getAppStoreCategory() {
		return appStoreCategory;
	}
	public void setAppStoreCategory(String appStoreCategory) {
		this.appStoreCategory = appStoreCategory;
	}

}
