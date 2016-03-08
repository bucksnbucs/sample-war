package edu.gatech.hiclass.spring.model;

import java.util.ArrayList;
import java.util.List;

public class LabData {
	private String name;
	private String report;
	private String date;
	private List<String[]> tableData = new ArrayList<String[]>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String[]> getTableData() {
		return tableData;
	}
	public void setTableData(List<String[]> tableData) {
		this.tableData = tableData;
	}

}
