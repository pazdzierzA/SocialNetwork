package com.solvd.socialnetwork.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPoolv2 {
	private final static Logger logger = LogManager.getLogger(ConnectionPoolv2.class.getName());
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3307/social_networks";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private BlockingQueue<Connection> connections;
	private Integer size;
	private static ConnectionPoolv2 INSTANCE = null;

	private ConnectionPoolv2(Integer size) {
		this.size = size;
		this.connections = new ArrayBlockingQueue<>(size);
		for (int i = 0; i < size; i++) {
			try {
				connections.offer(createNewConnection());

			} catch (SQLException e) {
				throw new RuntimeException("Error initializing connection pool", e);
			}
		}
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public static ConnectionPoolv2 getInstance(Integer size) {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionPoolv2(size);

		}
		return INSTANCE;
	}

	private Connection createNewConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public void releaseConnection(Connection connection) {
		if (connection != null) {
			logger.info(Thread.currentThread().getName() + " releasing connection: ");
			connections.offer(connection);
			logger.info("Queue state after release: " + connections);
		}
	}

	public Connection getConnection() throws InterruptedException {
		logger.info(Thread.currentThread().getName() + " waiting for connection... ");
		Connection connection = connections.take();
		logger.info(Thread.currentThread().getName() + " acquired connection: ");
		logger.info("Queue state after acquire: " + connections);
		return connection;
	}

	public void shutdown() {
		for (Connection conn : connections) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Error closing connection", e);
			}
		}
		connections.clear();
		logger.info("Connection pool has been shut down.");
	}

}
