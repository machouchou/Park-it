package com.parkit.parkingsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBaseConfig {

	/**
	 * 
	 */
	private static final Logger LOGGER = LogManager.getLogger("DataBaseConfig");

	/**
	 * getConnection configuration method allows to connect to database Mysql.
	 * 
	 * @return The Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		LOGGER.info("Create DB connection");
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/PROD?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=Europe/Paris",
				"root", "1975");
	}

	/**
	 * this method closes the DB connection.
	 * 
	 * @param con
	 */
	public void closeConnection(final Connection con) {
		if (con != null) {
			try {
				con.close();
				LOGGER.info("Closing DB connection");
			} catch (SQLException e) {
				LOGGER.error("Error while closing connection", e);
			}
		}
	}

	/**
	 * This method closes the prepared statement.
	 * 
	 * @param ps
	 */
	public void closePreparedStatement(final PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
				LOGGER.info("Closing Prepared Statement");
			} catch (SQLException e) {
				LOGGER.error("Error while closing prepared statement", e);
			}
		}
	}

	/**
	 * This method closes the result set.
	 * 
	 * @param rs
	 */
	public void closeResultSet(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				LOGGER.info("Closing Result Set");
			} catch (SQLException e) {
				LOGGER.error("Error while closing result set", e);
			}
		}
	}
}
