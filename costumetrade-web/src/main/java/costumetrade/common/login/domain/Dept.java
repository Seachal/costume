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
public class Dept implements Serializable {

	private static final long serialVersionUID = 43735826218165441L;

	public static final Integer TYPE_GUARANTEE = 1;

	public static final Integer TYPE_BANK = 2;
	
	public static final Integer TYPE_CAR = 3;

	/**
	 * 机构ID
	 */
	private Integer id;
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 机构编号
	 */
	private String code;

	/**
	 * 上级部门ID
	 */
	private Integer parentId;
	/**
	 * 上级部门编码
	 */
	private String parentCode;

	/**
	 * 机构ID
	 */
	private Integer orgId;
	/**
	 * 机构类型：1=担保机构，2=银行，3=车商，对应DEPT_ORG_ID到不同表
	 */
	private Integer orgType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 1 删除 0 未删除
	 */
	private Integer removed;

	/**
	 *  
	 */
	private Integer branch;

	/**
	 * 所属省ID
	 */
	private Integer provinceId;
	/**
	 * 所属市ID
	 */
	private Integer cityId;
	/**
	 * 所属市ID
	 */
	private Integer areaId;
	/**
	 * 地址
	 */
	private String address;
	private String linkman;
	private String phone;

	private Integer sort;

	private Integer depth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRemoved() {
		return removed;
	}

	public void setRemoved(Integer removed) {
		this.removed = removed;
	}

	public Integer getBranch() {
		return branch;
	}

	public void setBranch(Integer branch) {
		this.branch = branch;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the depth
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

}
