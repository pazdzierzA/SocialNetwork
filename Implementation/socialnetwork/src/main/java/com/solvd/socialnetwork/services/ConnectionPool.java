package com.solvd.socialnetwork.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {
	private final static Logger logger = LogManager.getLogger(ConnectionPool.class.getName());
	private HikariDataSource dataSource;
	private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3307/social_networks";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private Integer size;
	private static ConnectionPool INSTANCE = null;

	private ConnectionPool(Integer size) {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(JDBC_URL);
		config.setUsername(USERNAME);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(size);
		config.setMinimumIdle(2);
		config.setIdleTimeout(30000);
		config.setMaxLifetime(1800000);
		this.dataSource = new HikariDataSource();
	}

	public static ConnectionPool getInstance(Integer size) {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionPool(size);

		}
		return INSTANCE;
	}

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Unable to get connection from pool", e);
		}
	}

	public void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public int getAvailableConnections() {
		return dataSource.getHikariPoolMXBean().getIdleConnections();
	}

}