package com.vitim.device.beans;

import java.io.Serializable;

public class ResultBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String deviceId;
	String tenantId;
	String userId;
	Double result;

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

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

}
