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
import java.util.List;

public class FingraphSearchParam implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -6590497897729823879L;
	private String appkey;
	private String from;
	private String to;
	private String fromTo;
	private String period;
	private String term;
	private String segment;
	private int topN = 5; //topN data
	List<String> topNList;
	List<Integer> topNGrpList;
	private boolean isOthers = false;
	private String selectValue; //components - timeofday, topcountries
	private Integer groupkey;
	private String referrerkey;
	private String prokey; // WebPromotion PromotionKey

	//today snapshot
	private String today;
	private String nowTime;

    // Unit for revenue Snapshot
    private String unit;
    // Data domain for revenue Snapshot
    private String domain;
	
    // ad channel snapshot
    private String campaignkey;
    private String retentionkey;
    
	public FingraphSearchParam(){
		
	}
	
	public FingraphSearchParam(String appkey, String from, String to,
			String fromTo, String period, String term, String segment,
			int topN, List<String> topNList, List<Integer> topNGrpList,
			boolean isOthers, String selectValue, Integer groupkey,
			String referrerkey, String today, String nowTime,String unit,String domain,
			String campaignkey, String retentionkey) {
		super();
		this.appkey = appkey;
		this.from = from;
		this.to = to;
		this.fromTo = fromTo;
		this.period = period;
		this.term = term;
		this.segment = segment;
		this.topN = topN;
		this.topNList = topNList;
		this.topNGrpList = topNGrpList;
		this.isOthers = isOthers;
		this.selectValue = selectValue;
		this.groupkey = groupkey;
		this.referrerkey = referrerkey;
		this.today = today;
		this.nowTime = nowTime;
        this.unit = unit;
        this.domain = domain;
        this.campaignkey = campaignkey;
        this.retentionkey = retentionkey;
	}


}
