package com.solvd.socialnetwork.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
	private final static Logger logger = LogManager.getLogger(ConnectionPool.class.getName());
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3307/social_networks";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private BlockingQueue<Connection> connections;
	private static Integer size = 10;
	private static ConnectionPool INSTANCE = null;

	private ConnectionPool(Integer size) {
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


	public static ConnectionPool getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionPool(size);

		}
		return INSTANCE;
	}

	private Connection createNewConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	public void releaseConnection(Connection connection) {
		if (connection != null) {
			connections.offer(connection);
			
		}
	}

	public Connection getConnection() {
		try {
			return connections.take();
		} catch (InterruptedException e) {
			 Thread.currentThread().interrupt();
	         throw new RuntimeException("Connection waiting interrupted", e);
		}
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
