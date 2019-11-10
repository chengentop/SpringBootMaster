package com.master.core.framework.web;

import org.apache.commons.lang.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseService {

	/**
	 * 获取32位UUID
	 * 
	 * @return
	 */
	public String getUUID32Upercase() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public Map<String, Object> getFieldValueMap(Object bean) {
		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		if (bean == null) {
			return valueMap;
		}
		PropertyDescriptor[] props = getPropertyDescriptors(bean);
		for (PropertyDescriptor p : props) {
			Method getter = p.getReadMethod();
			if (!"class".equals(p.getName())) {
				try {
					Object value = getter.invoke(bean);
					if (null != value) {
						if (value instanceof String) {
							valueMap.put(p.getName(), StringUtils.trim((String) value));
						} else if (value instanceof Date) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							valueMap.put(p.getName(), sdf.format((Date) value));
						} else if (value instanceof Timestamp) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
							valueMap.put(p.getName(), sdf.format(new Date(((Timestamp) value).getTime())));
						} else {
							valueMap.put(p.getName(), getter.invoke(bean));
						}
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		return valueMap;
	}

	public List<Map<String, Object>> getFieldValueList(List<?> beans) {
		List<Map<String, Object>> valueList = new ArrayList<Map<String, Object>>();
		for (Object bean : beans) {
			valueList.add(getFieldValueMap(bean));
		}
		return valueList;
	}

	private static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
		Class<?> cls = bean.getClass();
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(cls);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if (null != beanInfo) {
			return beanInfo.getPropertyDescriptors();
		} else {
			return null;
		}
	}
}
