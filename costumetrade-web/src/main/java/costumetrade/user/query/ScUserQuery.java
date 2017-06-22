package costumetrade.user.query;

import costumetrade.common.Entity;
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
    private String permission;

    /**
     *  
     */
    private String menu;
    
    private SpEmployee employee;
    
    private StoreQuery query;
    
    private Integer userIdentity;
    
    private Integer userid;
    
    private Integer storeId;
    
    private Integer empId;
    

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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
    
    
}