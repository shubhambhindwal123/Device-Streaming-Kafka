package com.vitim.device.beans;

import java.io.Serializable;

public class ResponseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String deviceId;
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
	String tenantId;
	String userId;
}
