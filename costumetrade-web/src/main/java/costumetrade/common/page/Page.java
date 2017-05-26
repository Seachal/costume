
package costumetrade.common.page;

import java.io.Serializable;

/**
 * 
 * @author zhouyq
 * @Date 2017年1月5日
 */
public class Page implements Serializable {

	private static final long serialVersionUID = -1855574134779989167L;
	
	/**
	 * 总共页数
	 */
	private Integer pages;
	
	
	/**
	 * 总行数
	 */
	private long total;
	
	/**
	 * 行数
	 */
	private Integer pageSize;
	/**
	 * 当前页码
	 */
	private Integer pageNum;
	

	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize==null?10:pageSize;
	}

	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
		if (this.pageSize != 0) {
			calculate();
		}
	}
	
	private void calculate() {
		
		this.pages = (int) (this.total / this.pageSize);
		long lastPageSize = this.total % this.pageSize;
		if (lastPageSize > 0) {
			this.pages++;
		}
	}

	public Integer getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum==null?1:pageNum;
	}
	


	/**
	 * @return the pages
	 */
	public Integer getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(Integer pages) {
		this.pages = pages;
	}

	/**
	 * 起始行
	 * @return int 起始行
	 */
	public Integer getStartRow() {
		return (this.pageNum - 1) * this.pageSize;
	}
	
	
}
