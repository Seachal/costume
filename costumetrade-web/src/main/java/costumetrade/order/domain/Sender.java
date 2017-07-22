package costumetrade.order.domain;

import costumetrade.common.Entity;

public class Sender extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id="";

	private String name="";
	
	private String prov="";
	
	private String county="";
	
	private String zipcode="";
	
	private String company="";
	
	private String city="";
	
	private String address="";
	
	private String postcode="";
	
	private String phone="";
	
	private String mobile="";
	
	private String branch="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name  == null ? "" : name.trim();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company == null ? "" : company.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? "" : city.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? "" : address.trim();
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? "" : postcode.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? "" : phone.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? "" : mobile.trim();
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch == null ? "" : branch.trim();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? "" : id.trim();
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov == null ? "" : prov.trim();
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county == null ? "" : county.trim();
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode == null ? "" : zipcode.trim();
	}
	
	
	
}
