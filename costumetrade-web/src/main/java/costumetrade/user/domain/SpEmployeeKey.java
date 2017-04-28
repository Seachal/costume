package costumetrade.user.domain;

public class SpEmployeeKey {
    /**
     *  
     */
    private Integer corpid;

    /**
     *  员工编号:
     */
    private String id;

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}