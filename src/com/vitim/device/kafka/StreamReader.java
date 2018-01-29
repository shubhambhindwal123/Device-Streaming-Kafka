package com.vitim.device.kafka;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shaded.parquet.org.codehaus.jackson.map.ObjectMapper;

import com.vitim.device.beans.ResultBean;
import com.vitim.device.beans.VitimConstant;
import com.vitim.device.spark.KafkaRekeyMapper;
import com.vitim.device.spark.KafkaUserFilter;
import com.vitim.device.support.VitimDataProperties;

public class StreamReader {
static	Logger logger = (Logger) LoggerFactory.getLogger(StreamReader.class);

	public void streamReader() {
		logger.info("stream started");
		Properties props = new Properties();
		props.put("bootstrap.servers", VitimDataProperties.getInstance().getValue(VitimConstant.BOOTSTRAP_SERVERS));
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put(StreamsConfig.APPLICATION_ID_CONFIG,
				"wordcount-lambda-example");
		final Serde<String> stringSerde = Serdes.String();
		logger.info("kafka stream properties loaded");
		
		logger.info("starting kafka streaming using KStream Builder");
		KStreamBuilder builder = new KStreamBuilder();
		final KStream<String, String> source = builder.stream(stringSerde,
				stringSerde, VitimDataProperties.getInstance().getValue(VitimConstant.KAFKA_INPUT_TOPIC));
		logger.info("reading from source topic");
		KStream<KeyValue<String, String>, String> filtered = 	source.filter(new KafkaUserFilter()).selectKey(new KafkaRekeyMapper());
		logger.info("filtering and rekeyed data");
		KStream<String, String> rekeyed =filtered.selectKey((key, value) -> {
			ResultBean result = null;
			ObjectMapper mapper = new ObjectMapper();
			try {
				result = mapper.readValue(value, ResultBean.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String deviceId = result.getDeviceId();
			String tenantId = result.getTenantId();
			String userId = result.getUserId();
			String rekey = deviceId + "/" + tenantId + "/" + userId;
			return rekey;
		});
		
		
		
		rekeyed.foreach((key, value) -> System.out
				.println(key + " => " + value));
		logger.info("printinh rekeyed data");
		System.out.println("mapper successfull");
		rekeyed.to(stringSerde, stringSerde, VitimDataProperties.getInstance().getValue(VitimConstant.KAFKA_OUTPUT_TOPIC));
		System.out.println("dumptopic");
		final KafkaStreams streams = new KafkaStreams(builder, props);
		logger.info("cleanup  and starting streams ");
		streams.cleanUp();
		streams.start();

      logger.info("Add shutdown hook to respond to SIGTERM and gracefully close Kafka");		
		// Streams
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	}
	public static void main(String[] args) {
		StreamReader reader = new StreamReader();
		reader.streamReader();
	}
	
}
