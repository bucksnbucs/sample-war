package edu.gatech.hiclass.spring.model;

public class TableDTO {
	private Object[] data;
	
	public TableDTO() {
		data = null;
	}
	
	public TableDTO(Object[] data) {
		this.data = data;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}
	
	
}
