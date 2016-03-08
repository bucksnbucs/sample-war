package edu.gatech.hiclass.spring.model;

import java.util.ArrayList;
import java.util.List;

public class ChartData {
	
	private List<DataPoint> data;
	
	public ChartData() {
		this.data = new ArrayList<DataPoint>();
	}
	
	public List<DataPoint> getData() {
		return data;
	}
	public void setData(List<DataPoint> data) {
		this.data = data;
	}
	public void addDataPoint(String time, String value)
	{
		data.add(new DataPoint(time, value));
	}
}

class DataPoint {
	private String time;
	private String value;
	
	public DataPoint(String time, String value){
		this.time = time;
		this.value = value;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
