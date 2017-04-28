package costumetrade.order.domain;

public class SpPSizeKey {
    /**
     *  企业ID
     */
    private Integer corpid;

    /**
     *  识别编码:尺码名称，字符类型，允许1-10个字符（最多5个汉字）
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