package costumetrade.order.domain;

public class SpPrinterInfoKey {
	private static final long serialVersionUID = 1L;
    /**
     *  企业ID
     */
    private Integer corpid;

    /**
     *  分店ID
     */
    private String subid;

    public Integer getCorpid() {
        return corpid;
    }

    public void setCorpid(Integer corpid) {
        this.corpid = corpid;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid == null ? null : subid.trim();
    }
}