package test.vitim.streaming;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import com.vitim.device.kafka.KConsumer;
import com.vitim.device.support.VitimDataProperties;

public class StreamingTest {
	@Test
	public void Streaming(){
		KafkaConsumer<String, String> consumer = KConsumer.getInstance();
		consumer.subscribe(Arrays.asList(VitimDataProperties.getInstance()
				.getValue("KAFKA_TOPIC")));
		System.out.println("Subscribed to topic "
				+ VitimDataProperties.getInstance().getValue("KAFKA_TOPIC"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s\n",
						record.offset(), record.key(), record.value());
			}
		}
	}
	
}
