package com.zrodo.agriculture.util;


import java.util.List;

/**
 * 分页辅助类
 * 
 * @author xiash
 * 
 */
public class PageBean {

	public PageBean() {

	}

	/**
	 * 构造函数
	 * 
	 * @param rowsCount
	 * @param pageSize
	 */
	public PageBean(int rowsCount, int pageSize) {
		this.rowsCount = rowsCount;
		this.pageSize = pageSize;
	}

	/**
	 * 当前页的所有数据
	 */
	private List result;
	/**
	 * 页前页第一条数据的下标
	 */
	private int currentPageIndex;
	/**
	 * 当前页码
	 */
	private int currentPageNo = 1;

	/**
	 * 下一页页码
	 */
	private int nextPageNo;

	/**
	 * 上一页页码
	 */
	private int prePageNo;

	/**
	 * 第一页页码
	 */
	private int firstPageNo;

	/**
	 * 最后一页页码
	 */
	private int lastPageNo;

	/**
	 * 页的大小
	 */
	private int pageSize = 20;

	/**
	 * 总共记录数
	 */
	private int rowsCount;

	/**
	 * 总共页数
	 */
	private int pageCount;

	/**
	 * 排顺序名称
	 */
	private String orderByName;

	/**
	 * 排顺序方式(升 降)
	 */
	private String orderByMethod = "asc";
	/**
	 * 拼接查询条件：页开始行
	 */
	private int startRow;
	/**
	 * 拼接查询条件：页结束行
	 */
	private int endRow;

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * 得到下一页码
	 * 
	 * @return
	 */
	public int getNextPageNo() {
		this.nextPageNo = this.currentPageNo + 1;

		if (this.nextPageNo > this.getPageCount()) {
			this.nextPageNo = this.getPageCount();
		}
		return nextPageNo;
	}

	/**
	 * 得到总共页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		if (this.rowsCount % this.pageSize == 0) {
			this.pageCount = this.rowsCount / this.pageSize;
		} else {
			this.pageCount = (this.rowsCount / this.pageSize) + 1;
		}
		return pageCount;
	}

	/**
	 * 得到上一页
	 * 
	 * @return
	 */
	public int getPrePageNo() {
		this.prePageNo = this.currentPageNo - 1;
		if (this.prePageNo <= 0) {
			this.prePageNo = 1;
		}
		return prePageNo;
	}

	/**
	 * 得到第一页码
	 * 
	 * @return
	 */
	public int getFirstPageNo() {
		this.firstPageNo = 1;
		return firstPageNo;
	}

	/**
	 * 得到最后一页
	 * 
	 * @return
	 */
	public int getLastPageNo() {
		this.lastPageNo = this.getPageCount();
		return lastPageNo;
	}

	public String getOrderByName() {
		return orderByName;
	}

	public void setOrderByName(String orderByName) {
		this.orderByName = orderByName;
	}

	public String getOrderByMethod() {
		return orderByMethod;
	}

	public void setOrderByMethod(String orderByMethod) {
		this.orderByMethod = orderByMethod;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public int getCurrentPageIndex() {
		this.currentPageIndex = (this.getCurrentPageNo() - 1) * pageSize;
		return this.currentPageIndex;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 当前页是否是第一页
	 * 
	 * @return
	 */
	public boolean getIsFirst() {
		return this.getCurrentPageNo() == 1;
	}

	/**
	 * 当前页是否是最后一页
	 * 
	 * @return
	 */
	public boolean getIsLast() {
		return this.getCurrentPageIndex() == this.getLastPageNo();
	}

	/**
	 * 设置数据行总数，并判断当前页是第几页,初始化页首页尾行号
	 * 
	 * @param rowsCount
	 */
	public void build(int rowsCount) {
		setRowsCount(rowsCount);
		if (this.getCurrentPageNo() <= this.getFirstPageNo()) {
			this.setCurrentPageNo(this.getFirstPageNo());
		}else if (this.getCurrentPageNo() > this.getLastPageNo()) {
			this.setCurrentPageNo(this.getLastPageNo());
		}
		setStartRow((this.getCurrentPageNo() - 1) * this.getPageSize()+ 1);
		setEndRow(this.getCurrentPageNo() * this.getPageSize());
	}
}
