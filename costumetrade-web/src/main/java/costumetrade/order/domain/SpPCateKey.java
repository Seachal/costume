package costumetrade.order.domain;

public class SpPCateKey {
    /**
     *  企业ID
     */
    private Integer corpid;

    /**
     *  种类编码
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