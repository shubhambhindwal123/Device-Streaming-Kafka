package com.vitim.device.spark;

import java.io.IOException;
import java.util.List;

import org.apache.kafka.streams.kstream.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitim.device.beans.ResultBean;
import com.vitim.device.cassandra.CassandraConnector;

public class KafkaUserFilter implements Predicate<String, String> {

	final CassandraConnector client = new CassandraConnector();
	ObjectMapper mapper = new ObjectMapper();
	static Logger logger = LoggerFactory.getLogger(KafkaUserFilter.class);

	Session session = client.connect("localhost", 9042);

	@Override
	public boolean test(String key, String values) {

		String users;
		logger.info("inside Kafka User Filter");
		ResultBean user = new ResultBean();
		try {
			user = mapper.readValue(values, ResultBean.class);
			logger.info("converting value into result bean using value");
			System.out.println("we are getting data " + user.getUserId());
		} catch (IOException e) {

			System.out.println("mapper readValue error");
		}

		/*
		 * if (flag == true) { try { responseUser = mapper.readValue(values,
		 * ResponseBean.class); } catch (IOException e) { e.printStackTrace(); }
		 * users = responseUser.getUserId() + ";"; } else
		 */
		users = user.getUserId();

		logger.info("reading key and maching it with database for filtering the users ");
		ResultSet data = (ResultSet) session
				.execute("select * from vitimkeyspace.job_subscription where jobid ='" + key + "';");
		Row row = data.one();

		if (row == null) {

			return false;
		}

		List<String> ids = row.getList("consumerList", String.class);

		for (int i = 0;i < ids.size(); i++) {
			ResultBean requestedUser = null;
			try {
				String element = ids.get(i);
				requestedUser = mapper.readValue(element, ResultBean.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (users.equals(requestedUser.getUserId())) {
				return true;
			}
			
		}

		return false;

	}

}
