package com.master.core.framework.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.master.core.framework.exception.BpmsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.master.core.framework.Constant;


public class ResponseData {
	private Logger log = LoggerFactory.getLogger(ResponseData.class);
	// 需要显示的数据集
	@SuppressWarnings("rawtypes")
	private List<?> rows = new ArrayList();
	// 每页显示数量
	private int page = 1;
	// 总页数
	private int total = 1;
	// 数据总数
	private int records = 0;
	// 加载成功
	private Boolean successed = true;
	// 自定义数据
	private Map<String, Object> error;

	public static ResponseData init() {
		return new ResponseData();
	}

	public ResponseData setData(List<?> list, int count, RequestData reqData) {
		this.setRows(list);
		this.setPage(reqData.getPage());
		this.setRecords(count);
		this.setTotal(totalPage(count, reqData.getRows()));
		return this;
	}

	public ResponseData setError(Exception e) {
		this.setSuccessed(false);
		this.error = new HashMap<String, Object>();
		if (e instanceof BpmsException) {
			BpmsException re = (BpmsException) e;
			this.error.put("code", re.getCode());
			this.error.put("reason", re.getReason());
		} else {
			this.error.put("code", Constant.DEFAULT_ERROR_CODE);
			this.error.put("reason", "未知错误");
		}
		log.error("Exception: ", e);
		return this;
	}

	private int totalPage(int count, int rows) {
		return count % rows == 0 ? count / rows : count / rows + 1;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Boolean getSuccessed() {
		return successed;
	}

	public void setSuccessed(Boolean successed) {
		this.successed = successed;
	}

	public Map<String, Object> getError() {
		return error;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("time=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sb.append(", page=").append(page);
		sb.append(", pages=").append(total);
		sb.append(", total=").append(records);
		sb.append(", rows=").append(rows.size());
		sb.append("}");
		return sb.toString();
	}
}
