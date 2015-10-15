package com.loukou.auth.resp.dto.base;

import java.io.Serializable;
import java.util.List;


public class ListDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6511479914503310513L;
	private String message;
	private List<T> list;
	private long total = 0L;
	
	public ListDto(String message, List<T> list) {
		this.message = message;
		this.list = list;
	}
	
	public ListDto(String message, List<T> list, long total) {
		this.message = message;
		this.list = list;
		this.total = total;
	}
	
	
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}

