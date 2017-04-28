package costumetrade.user.domain;

import costumetrade.common.Entity;

public class ScUser extends Entity {
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
}