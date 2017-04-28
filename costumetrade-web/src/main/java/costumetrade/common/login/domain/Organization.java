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
public class Organization implements Serializable{
	
	private static final long serialVersionUID = 8454487102762305055L;

	private Integer id;
	
	private String name;
	
	private String code;

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

	
	
	
	
	

}
