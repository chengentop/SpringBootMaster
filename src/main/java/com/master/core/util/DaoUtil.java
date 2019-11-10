package com.master.core.util;

import com.master.core.framework.db.ICommonDao;

public class DaoUtil {
	private static ICommonDao commonDao;
	
	public DaoUtil(ICommonDao commonDao) {
		DaoUtil.commonDao = commonDao;
	}

	public static ICommonDao getCommonDao() {
		return commonDao;
	}
	
}
