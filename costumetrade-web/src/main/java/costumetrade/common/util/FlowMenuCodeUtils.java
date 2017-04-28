/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年1月27日
 */
package costumetrade.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is Class Description...
 * @author liyb
 * @version FlowMenuCodeUtils.java,2016年1月27日 下午8:50:09
 */
public class FlowMenuCodeUtils implements Serializable {

    private static final long serialVersionUID = -2620304996642724173L;
    
    private static Map<String,Object> map = new HashMap<String, Object>();
    
    private static void initMap(){
        //业务云
        map.put("myCustomer", "myCustomer");
        map.put("allCustomer", "allCustomer");
        map.put("printContract", "printContract");
        map.put("signTaskOrderList", "signTaskOrderList");
        
        //新短融
        map.put("currentLendManage", "currentLendManage");
        map.put("currentLoanManage", "currentLoanManage");
        map.put("shortFinancialAssignConfig", "shortFinancialAssignConfig");
        
        //逾期管理
        map.put("safetyConfig", "safetyConfig");
        map.put("overdueConfig", "overdueConfig");
        map.put("phoneCollectionConfig", "phoneCollectionConfig");
        map.put("lineCollectionConfig", "lineCollectionConfig");
        map.put("legalConfig", "legalConfig");
        map.put("overdueInputConfig", "overdueInputConfig");
        map.put("registPledgeConfig", "registPledgeConfig");
    }

    /**
     * 校验菜单是否存在
     * @param code
     * @return
     */
    public static boolean verifyMenuCode(String code){
        initMap();
        if(map.containsKey(code)){
            return true;
        }
        return false;
    }
    
    /**
     * 比对值是否相等，
     * @param param1 比对
     * @param param2 被比对
     * @return
     */
    public static final boolean compareValue(String param1, String param2){
    	if(null != param2 && !"".equals(param2)){
    		String[] str = param2.split(",");
    		List<String> list = new ArrayList<String>();
    		for (int i = 0; i < str.length; i++) {
    			list.add(str[i]);
			}
    		
    		if(list.contains(param1)){
    			return true;
    		}
    	}else{
    		return false;
    	}
		return false;
    }
}
