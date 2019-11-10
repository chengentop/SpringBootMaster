package com.master.core.framework.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PagerBean implements Serializable{

	public static final int MaxPageSize = 1024;	//单页最大数据量
	
	private static final long serialVersionUID = 1L;
	private Integer pageSize;
	private Integer offSet;  //offSet=pageSize*currentPage
	private Integer totalSize;
	private Integer totalPage;
	private Integer currentPage;
	private String orderKey;
	private String ascend;
	
	/**
	 * 供用默认页大小配置查询所有信息的首页部分操作使用
	 */
	public PagerBean(){}
	/**
	 * 供查询所有记录中的分页操作使用
	 * @param pageSize
	 * @param currentPage
	 */
	public PagerBean(Integer pageSize, Integer currentPage){
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.offSet = (currentPage-1) * pageSize;
	}
	/**
	 * 在总大小和页信息设定后，根据设定值判断是否能够查询到数据
	 * @return 可以查到数据则返回true，否则返回false
	 */
	public boolean canFind(){
		if(offSet>totalSize){
			return false;
		}else{
			return true;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(currentPage!=null)
			offSet = pageSize*(currentPage-1); //更新偏移量
	}
	public Integer getOffSet() {
		return offSet;
	}
	public Integer getTotalSize() {
		return totalSize;
	}
	//偏移量不能直接写
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
		if(pageSize!=null)
			offSet = pageSize*(currentPage-1); //更新偏移量
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	public String getOrderKey() {
		return orderKey;
	}
	public String toString(){
		Object[] objs = {currentPage==null?"?":currentPage, pageSize==null?"?":pageSize, totalSize==null?"?":totalSize, 
				totalPage==null?"?":totalPage, orderKey==null?"?":orderKey, offSet==null?"?":offSet, ascend==null?"?":ascend};
						return String.format("[currentPage=%s, pageSize=%s, totalSize=%s, totalPage=%s, orderKey=%s, offSet=%s, ascend=%s]", objs);
						}
public Map<String, Object> parseMap() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageSize", pageSize);
		result.put("offSet", offSet);
		result.put("totalSize", totalSize);
		result.put("totalPage", totalPage);
		result.put("currentPage", currentPage);
		result.put("orderKey", orderKey);
		result.put("ascend", ascend);
		return result;
		}
public String getAscend() {
		return ascend;
		}
public void setAscend(String ascend) {
		this.ascend = ascend;
		}
		}
