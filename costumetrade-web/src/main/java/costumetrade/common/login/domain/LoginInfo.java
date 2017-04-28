package costumetrade.common.login.domain;

import java.io.Serializable;
import java.util.Set;

import costumetrade.common.Entity;
import costumetrade.common.login.LoginAgent;

public class LoginInfo implements Serializable {
	
	private static final long serialVersionUID = -3104497334502106207L;
	
	private Integer id;
	private String name;
	private String account;
	
	private String phone1;
	
	private String busiSource;
	
	private Integer orgId;
	
	private String loginAgent;
	
	private Organization organization;
	
	private Dept dept;
	private Role role;
	
	private Set<? extends Entity> funcs;
	
	private Set<? extends Entity> menus;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getBusiSource() {
		return busiSource;
	}

	public void setBusiSource(String busiSource) {
		this.busiSource = busiSource;
	}

	public String getLoginAgent() {
		return loginAgent;
	}

	public void setLoginAgent(String loginAgent) {
		this.loginAgent = loginAgent;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 *  @return
	 */
	public boolean fromApp() {
		
		return LoginAgent.isApp(this.loginAgent);
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the phone1
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1 the phone1 to set
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 *  @return
	 */
	public Integer getDeptId() {

		return dept==null?null:dept.getId();
	}
	
	public String getDeptCode(){
		return dept==null?null:dept.getCode();
	}

	/**
	 * @return the funcs
	 */
	public Set<? extends Entity> getFuncs() {
		return funcs;
	}

	/**
	 * @param funcs the funcs to set
	 */
	public void setFuncs(Set<? extends Entity> funcs) {
		this.funcs = funcs;
	}

	/**
	 * @return the menus
	 */
	public Set<? extends Entity> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(Set<? extends Entity> menus) {
		this.menus = menus;
	}
	
	
	
}
