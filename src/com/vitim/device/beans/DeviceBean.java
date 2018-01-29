package com.vitim.device.beans;

import java.io.Serializable;

public class DeviceBean implements Serializable {
	/**
	 * 
	 * This DeviceBean class is used for load
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String devicesession;
	String deviceId;
	String deviceName;
	int devicePresure;
	String deviceTempreture;
	String devicetime;
	String deviceStatus;
	String tenantId;
	String userId;

	public String getDevicesession() {
		return devicesession;
	}

	public void setDevicesession(String devicesession) {
		this.devicesession = devicesession;
	}

	public String getDevicetime() {
		return devicetime;
	}

	public void setDevicetime(String devicetime) {
		this.devicetime = devicetime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {

		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getDevicePresure() {
		return devicePresure;
	}

	public void setDevicePresure(String devicePresure) {
		int pressure = Integer.parseInt(devicePresure);
		this.devicePresure = pressure;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceTempreture() {
		return deviceTempreture;
	}

	public void setDeviceTempreture(String deviceTempreture) {
		this.deviceTempreture = deviceTempreture;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
