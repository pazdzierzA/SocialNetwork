package com.solvd.socialnetwork.utils.connectionpools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.socialnetwork.utils.Implementation;

public class ConnectionPool extends Implementation {
	private final static Logger logger = LogManager.getLogger(ConnectionPool.class.getName());
	private static final String PROPERTIES_FILE = "_database.properties";
	private static final String JDBC_URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	static {
		String tempUrl = null;
		String tempUserName = null;
		String tempPassword = null;
		try {
			tempUrl = Objects.requireNonNull(loadImplementation(PROPERTIES_FILE).getProperty("db.url"));
			tempUserName = Objects.requireNonNull(loadImplementation(PROPERTIES_FILE).getProperty("db.username"));
			tempPassword = Objects.requireNonNull(loadImplementation(PROPERTIES_FILE).getProperty("db.password"));
		} catch (IOException e) {
			logger.error("Error loading database properties from properties file", e);
		}
		JDBC_URL = tempUrl;
		USERNAME = tempUserName;
		PASSWORD = tempPassword;
	}

	private BlockingQueue<Connection> connections;
	private static Integer size = 10;
	private static ConnectionPool INSTANCE = null;


	public static ConnectionPool getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionPool(size);

		}
		return INSTANCE;
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
	
	public Integer getSize() {
		return size;
	}

	
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

	private Connection createNewConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

}
