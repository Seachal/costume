package costumetrade.order.domain;

public class SpPColorCustomKey {
    /**
     *  企业ID
     */
    private Integer corpid;

    /**
     *  颜色编号
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