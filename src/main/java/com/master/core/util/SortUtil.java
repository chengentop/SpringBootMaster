package com.master.core.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
/**
 * 排序Util
 * @author zhangkui
 *
 */
public class SortUtil {
	/**
	 * 得到签名前的排序
	 * 返回排序后的串
	 * @param map
	 * @return
	 */
	public static String getSortBeforeSign(Map<String, String>  map){
		List<Map.Entry<String, String>> infoIds =new ArrayList<Map.Entry<String, String>>(map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {   
		    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {      
		        return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		}); 
		StringBuilder sortstr = new StringBuilder();
		int step = 0;
		for (Map.Entry<String, String> entry : infoIds) {
			if (step == (infoIds.size() - 1)) {
				sortstr.append(entry.getKey() + "=" + entry.getValue());
			} else {
				sortstr.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
			step++;
		}
		return sortstr.toString();
	}

}
