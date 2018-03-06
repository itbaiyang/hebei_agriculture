package com.zrodo.agriculture.util;

import java.util.List;

/**
 * 分页类
 *
 * @author wangmin
 * <p>
 * 2016年7月25日
 */
public class Page {

//	public static void main(String[] args)
//	{
//		List<?> list = new ArrayList<>();
//	
//		Page page = new Page(list, 5, 15, 61);
//		JSONObject fromObject = JSONObject.fromObject(page);
//		System.out.println(fromObject.toString());
//	}


    /**
     * 构造器
     *
     * @param rows       内容
     * @param pageNo     当前页页码
     * @param pageSize   每页几条数据
     * @param totalCount 总共几条数据
     */
    public Page(List<?> list, int pageNo, int pageSize, int totalCount) {
        this.rows = list;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = getTotalPage();
        this.prePage = getPrePage();
        this.nextPage = getNextPage();
        this.lastPage = pageNo == totalPage;
        this.firstPage = pageNo == 1;
        this.firstResult = (pageNo - 1) * pageSize;
    }

    /**
     * 当前页数据
     */
    private List<?> rows;

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 当前页页码
     */
    private int pageNo;

    /**
     * 每页几条数据
     */
    private int pageSize;

    /**
     * 总共几条数据
     */
    private int totalCount;

    /**
     * 总共多少页
     */
    private int totalPage;

    public int getTotalPage() {
        if (totalCount < 0) {
            return -1;
        }

        int count = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * 上一页页码
     */
    private int prePage;

    /**
     * 取得上页的页号, 序号从1开始.
     * 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (pageNo - 1 >= 1) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }

    /**
     * 下一页页码
     */
    private int nextPage;

    /**
     * 取得下页的页号, 序号从1开始.
     * 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (pageNo + 1 <= getTotalPage()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }

    /**
     * 第一页
     */
    private boolean firstPage;

    public boolean isFirstPage() {
        return firstPage;
    }

    /**
     * 第一条数据位置
     */
    private int firstResult;

    /**
     * 第一条数据位置
     *
     * @return
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * 最后一页
     */
    private boolean lastPage;

    public boolean isLastPage() {
        return lastPage;
    }
}

