package com.vitim.device.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

import static java.lang.System.out;

/**
 * Class used for connecting to Cassandra database.
 */
public class CassandraConnector {
	static Logger logger = LoggerFactory.getLogger(CassandraConnector.class);

	private Cluster cluster;
	private Session session;

	/**
	 * Connect to Cassandra Cluster specified by provided node IP address and
	 * port number.
	 *
	 * @param node
	 *            Cluster node IP address.
	 * @param port
	 *            Port of cluster host.
	 * @return
	 */
	public Session connect(final String node, final int port) {
		logger.info("Inside connect method of CassandraConnector");
		this.cluster = Cluster.builder().addContactPoint(node).withPort(port)
				.build();
		final Metadata metadata = cluster.getMetadata();
		logger.info("Connected to cluster: %s\n", metadata.getClusterName());
		for (final Host host : metadata.getAllHosts()) {
			out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
			logger.info("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());

		}
		session = cluster.connect();
		logger.info("returning session and exting for CassandraConnection ");
		return session;
	}

	/**
	 * Provide my Session.
	 *
	 * @return My session.
	 */
	public Session getSession() {
		return this.session;
	}

	/** Close cluster. */
	public void close() {
		cluster.close();
	}
}