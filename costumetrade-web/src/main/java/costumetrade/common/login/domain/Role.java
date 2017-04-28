/**
 * Copyright (C) 2014-2017, Hrfax and/or its affiliates. All rights reserved.
 * Hrfax PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package costumetrade.common.login.domain;

import java.io.Serializable;

/**
 * @author zhouyq
 * @Date 2017年1月22日
 */
public class Role implements Serializable{

	private static final long serialVersionUID = -483396544973256303L;
	
	/**
     *  角色ID
     */
    private Integer id;

    /**
     *  机构ID
     */
    private Integer orgId;
    /**
     * 机构类型：1=担保机构，2=银行，3=车商，对应DEPT_ORG_ID到不同表
     */
    private Integer orgType;


    /**
     *  直属部门ID
     */
    private Integer deptId;

    /**
     *  1 系统管理员admin
     */
    private Integer type;

    /**
     *  角色名称
     */
    private String name;

    /**
     *  角色显示名称
     */
    private String displayName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
