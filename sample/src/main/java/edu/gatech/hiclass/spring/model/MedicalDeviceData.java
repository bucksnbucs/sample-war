package edu.gatech.hiclass.spring.model;

public class MedicalDeviceData {
	private String manufacturer;
	private String model;
	private String device;
	
	public MedicalDeviceData(String manufacturer, String model, String device) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.device = device;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	
}
