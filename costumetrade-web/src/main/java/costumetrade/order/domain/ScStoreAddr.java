package costumetrade.order.domain;

import costumetrade.common.Entity;

public class ScStoreAddr extends Entity {
    /**
     *  主键
     */
    private Integer id;

    /**
     *  店铺ID主键
     */
    private Integer storeid;

    /**
     *  收货人名:收货联系人名称
     */
    private String contact;

    /**
     *  收货电话:收货联系人电话
     */
    private String phone;

    /**
     *  收货地址:收货人详细地址
     */
    private String address;

    /**
     *  是否默认
     */
    private Boolean isdefault;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }
}