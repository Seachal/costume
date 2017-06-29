package costumetrade.user.query;

import java.util.List;

import costumetrade.common.Entity;

public class DataDictionaryQuery extends Entity {
    /**
     *  
     */
    private Integer id;
    
    private String storeId;

    /**
     *  类型名称
     */
    private String typename;

    /**
     *  积分需求
     */
    private Integer integration;

    /**
     *  销售价类型：1、吊牌价；2、批发价；3、打包价；4、零售价
     */
    private String saletype;
    /*
     * 商品可见级别
     * */
    private String visibleGrade;
    
    
    private List<String> gradeList;
    
    private List<String> priceNameList;
    
    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getTypename() {
	        return typename;
	}

	public void setTypename(String typename) {
	        this.typename = typename == null ? null : typename.trim();
	}

	public Integer getIntegration() {
	        return integration;
	}

	public void setIntegration(Integer integration) {
	        this.integration = integration;
	}

	public String getSaletype() {
	        return saletype;
	}

	public void setSaletype(String saletype) {
	        this.saletype = saletype == null ? null : saletype.trim();
	}


	public List<String> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<String> gradeList) {
		this.gradeList = gradeList;
	}

	public List<String> getPriceNameList() {
		return priceNameList;
	}

	public void setPriceNameList(List<String> priceNameList) {
		this.priceNameList = priceNameList;
	}
	public String getVisibleGrade() {
		return visibleGrade;
	}

	public void setVisibleGrade(String visibleGrade) {
		this.visibleGrade = visibleGrade  == null ? null : visibleGrade.trim();;
	}

}