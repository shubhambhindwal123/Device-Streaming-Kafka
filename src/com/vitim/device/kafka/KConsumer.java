package com.vitim.device.kafka;

import java.io.Serializable;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vitim.device.beans.VitimConstant;
import com.vitim.device.support.VitimDataProperties;

public class KConsumer implements Serializable {
	String topic;

	public KConsumer(String topic) {
		this.topic = topic;
	}

	static Logger logger = LoggerFactory.getLogger(KConsumer.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static KafkaConsumer<String, String> consumer = null;

	public static KafkaConsumer<String, String> getInstance() {
		String group = "testgroup";
		Properties props = new Properties();
		props.put("bootstrap.servers", VitimDataProperties.getInstance().getValue(VitimConstant.BOOTSTRAP_SERVERS));
		props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		consumer = new KafkaConsumer<String, String>(props);

		return consumer;
	}

}
