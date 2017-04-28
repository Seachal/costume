/**
 * Copyright (C) 2014-2016, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.login;

import costumetrade.common.login.domain.Dept;
import costumetrade.common.login.domain.LoginInfo;
import costumetrade.common.login.domain.Organization;
import costumetrade.common.login.domain.Role;

/**
 * @author zhouyq
 * @Date 2016年12月24日
 */
public class LoginContext {
	
	private static ThreadLocal<LoginInfo> loginInfoHolder = new  ThreadLocal<>();
	
	public static LoginInfo get(){
		return loginInfoHolder.get();
	}
	
	public static void remove(){
		loginInfoHolder.remove();
	}
	
	public static void put(LoginInfo loginInfo){
		loginInfoHolder.set(loginInfo);
	}

	public static Integer getUserId() {
		LoginInfo loginInfo = get();
		return loginInfo == null?null:loginInfo.getId();
	}

	public static String getUserName() {
		LoginInfo loginInfo = get();
		return loginInfo == null?null:loginInfo.getName();
	}
	
	public static Integer getDeptId() {
		LoginInfo loginInfo = get();
		return loginInfo == null?null:loginInfo.getDeptId();
	}
	public static String getDeptCode(){
		LoginInfo loginInfo = get();
		return loginInfo == null?null:loginInfo.getDeptCode();
	}

	/**
	 *  @return
	 */
	public static String getAccount() {
		
		LoginInfo loginInfo = get();
		return loginInfo == null?null:loginInfo.getAccount();
	}
	
	public static String getLoginSource() {

		return LoginSource.GUARANTEE_CORP.name();
	}

	/**
	 *  获取机构编码
	 * @return 
	 */
	public static String getOrgCode() {
		
		return "nfdb";
		
	}

	/**
	 *  @return
	 */
	public static Integer getOrgId() {
		LoginInfo loginInfo = get();
		if(loginInfo == null){
			return null;
		}
		Dept dept = loginInfo.getDept();
		return dept == null?null:dept.getOrgId();
	}

	/**
	 *  @return
	 */
	public static String getRoleName() {
	    LoginInfo loginInfo = get();
        if(loginInfo == null){
            return null;
        }
        Role role = loginInfo.getRole();
		return role.getName();
	}

	/**
	 *  @return
	 */
	public static String getPhone() {
		// TODO Auto-generated method stub
		LoginInfo loginInfo = get();
        return loginInfo == null?null:loginInfo.getPhone1();
//        return "188888888";
	}

	/**
	 *  @return
	 */
	public static Integer getRoleId() {
	    LoginInfo loginInfo = get();
	    if(loginInfo == null){
            return null;
        }
	    Role role = loginInfo.getRole();
		return role.getId() ;
	}

	/**
	 *  @return
	 */
	public static String getOrgName() {
		LoginInfo loginInfo = get();
		if(loginInfo == null){
			return null;
		}
		Organization org = loginInfo.getOrganization();
		return org.getName();
	}


}
