package com.master.core.util;

import com.master.core.framework.db.PagerBean;
import com.master.core.framework.db.PageType;

public class PagerBeanUtil {
	public static PagerBean initialize(int pageSize){
		PagerBean page = new PagerBean();
		page.setCurrentPage(1);
		page.setPageSize(pageSize);
		return page;
	}
	/**
	 * 初始化PagerBean
	 * @param page
	 * @param type
	 * @return 传入的page对象和返回的page对象都会被初始化
	 */
	public static PagerBean initialize(PagerBean page, PageType type){
		if(null != page){
			if(null == page.getCurrentPage()) page.setCurrentPage(1);
			if(null == page.getPageSize()) page.setPageSize(type.getPageSize());
		}else{
			page = new PagerBean();
			page.setCurrentPage(1);
			page.setPageSize(type.getPageSize());
		}
		return page;
	}
}
