package com.vitim.device.spark;

import java.io.IOException;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitim.device.beans.DeviceBean;

public class KafkaRekeyMapper implements KeyValueMapper<String,String,KeyValue<String,String>> {
ObjectMapper mapper = new ObjectMapper();
static Logger logger = LoggerFactory.getLogger(KafkaRekeyMapper.class);

DeviceBean user;
	@Override
	public
	 KeyValue<String, String> apply(String key, String value) {
		logger.info("inside kafka Rekey Mapper");
		try {
			 user = mapper.readValue(value, DeviceBean.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String deviceId = user.getDeviceId();
		String deviceName = user.getDeviceName();
		String tenantId = user.getTenantId();
		String userId = user.getUserId();
		
		String rekey = deviceId+"/"+deviceName+"/"+tenantId+"/"+userId;
		
		KeyValue<String,String> keyvalue = new KeyValue<String,String>(rekey, value);

		return keyvalue;
	}

	
}
