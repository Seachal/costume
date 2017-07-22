package costumetrade.user.query;

import java.util.List;

import costumetrade.common.Entity;
import costumetrade.product.domain.ScPromotionalProduct;
import costumetrade.report.domain.ReportQuery;
import costumetrade.user.domain.SpEmployee;

public class ScUserQuery extends Entity {
    /**
     *  
     */
    private String id;

    /**
     *  
     */
    private String password;

    /**
     *  
     */
    private String name;

    /**
     *  
     */
    private String sessionKey;

    /**
     *  
     */
    private String menu;
    
    private SpEmployee employee;
    
    private StoreQuery query;
    
    private Integer userIdentity;
    
    private String userid;
    
    private String storeId;
    
    private String empId;
    
    private String openid ;
    
    private String photo;
    

    private ReportQuery report;
    
    private Integer fansCount;
    
    private List<ScPromotionalProduct> products;
    
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    

    public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

	public SpEmployee getEmployee() {
		return employee;
	}

	public void setEmployee(SpEmployee employee) {
		this.employee = employee;
	}

	public StoreQuery getQuery() {
		return query;
	}

	public void setQuery(StoreQuery query) {
		this.query = query;
	}

	public Integer getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(Integer userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<ScPromotionalProduct> getProducts() {
		return products;
	}

	public void setProducts(List<ScPromotionalProduct> products) {
		this.products = products;
	}

	public ReportQuery getReport() {
		return report;
	}

	public void setReport(ReportQuery report) {
		this.report = report;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}
    
    
}