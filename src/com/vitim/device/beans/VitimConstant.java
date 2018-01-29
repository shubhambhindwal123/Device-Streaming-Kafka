package com.vitim.device.beans;

import java.io.Serializable;


public class VitimConstant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CASSANDRA_KEYSPACE = "vitimkeyspace";
	public static final String CASSANDRA_TABLE = "devicedata";
	public static final String KAFKA_SERVER = "bootstrap.servers";
	public static final String KAFKA_HOST = "localhost:9092";
	public static final String BOOTSTRAP_SERVERS = "BOOTSTRAP_SERVERS";
	public static final String KAFKA_INPUT_TOPIC = "KAFKA_INPUT_TOPIC";
	public static final String KAFKA_OUTPUT_TOPIC = "KAFKA_OUTPUT_TOPIC";

	

}
