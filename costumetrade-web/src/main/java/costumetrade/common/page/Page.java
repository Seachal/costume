
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
	private int pages;
	/**
	 * 行数
	 */
	private int pageSize = 20;
	
	/**
	 * 总行数
	 */
	private long total;
	/**
	 * 当前页码
	 */
	private int pageNum = 1;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * 起始行
	 * @return int 起始行
	 */
	public int getStartRow() {
		return (this.pageNum - 1) * this.pageSize;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}
}
