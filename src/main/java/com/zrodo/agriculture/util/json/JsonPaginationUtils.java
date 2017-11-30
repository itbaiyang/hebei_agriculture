package com.zrodo.agriculture.util.json;


import com.zrodo.agriculture.util.PageBean;

import java.util.Map;


public class JsonPaginationUtils {
	public static void addPagination(Map<String, Object> map, PageBean pg) {
		map.put("pgNumber", String.valueOf(pg.getCurrentPageNo()));// 当前页
//		int pgCount = pg.getRowsCount() % pg.getPageSize() == 0 ? pg
//				.getTotalCount() / pg.getLimit() : pg.getTotalCount()
//				/ pg.getLimit() + 1;
		map.put("pgCount", String.valueOf(pg.getPageCount()));//总页数
		map.put("curCount", String.valueOf(pg.getResult().size()));//当前页条数
		map.put("limit", String.valueOf(pg.getPageSize()));// 每页条数
		map.put("totalCount", String.valueOf(pg.getRowsCount()));// 总条数
	}
}
