package com.vitim.device.spark;

import java.io.Serializable;

import com.datastax.spark.connector.japi.CassandraRow;
import com.vitim.device.beans.DeviceBean;

import org.apache.spark.api.java.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CassandraMapper implements Function<CassandraRow,DeviceBean>,Serializable{

	/**
	 * 
	 */
	static Logger logger = LoggerFactory.getLogger(CassandraMapper.class);
	private static final long serialVersionUID = 1L;
   
	@Override
	public DeviceBean call(CassandraRow cassandraRow) throws Exception {
logger.info("reading "+cassandraRow);
		DeviceBean devicebean = new DeviceBean();
		devicebean.setDevicesession(cassandraRow.getString("device_session"));
		devicebean.setDeviceName(cassandraRow
				.getString("device_name"));
		devicebean.setDeviceId(cassandraRow.getString("device_id"));
		devicebean.setDevicePresure(cassandraRow
				.getString("device_pressure"));
		devicebean.setDeviceTempreture(cassandraRow
				.getString("device_tempreture"));
		devicebean.setTenantId(cassandraRow.getString("tenantid"));
		devicebean.setUserId(cassandraRow.getString("userid"));
		devicebean.setDeviceStatus(cassandraRow
				.getString("device_status"));
		devicebean.setDevicetime(cassandraRow.getString("device_time"));
		
		return devicebean;	
	}

}
