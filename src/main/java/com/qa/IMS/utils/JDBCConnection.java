package com.qa.IMS.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	
	
	// url, user password
	private static final String DB_URL = "jdbc:mysql://35.204.203.151/orderSys?db_name&serverTimezone=UTC";
	
	private static String DB_USER; 
	
	private static String DB_PASSWORD; 
	
	private static JDBCConnection instance; 
	
	
	// singleton 
	private JDBCConnection(String username, String password) {
		JDBCConnection.DB_USER = username; 
		JDBCConnection.DB_PASSWORD = password; 
	}
	
	// get connection method 
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL,DB_USER, DB_PASSWORD);
	}
	
	// something to access out private constructor 
	public static JDBCConnection connect(String DB_USER, String DB_PASSWORD) {
		if (instance == null) {
			instance = new JDBCConnection(DB_USER, DB_PASSWORD);
		}
		return instance; 
	}
	
	// something to read through our executions 
	// insert, updating, deleting
	public void executeUpdate(String query) throws SQLException {
		Connection con = getConnection(); 
		Statement stmt = con.createStatement(); 
		stmt.executeUpdate(query);
	}
	
	// read from the db 
	// executeQuery - return type is table of data - ResultSet
	public ResultSet executeQuery(String query) throws SQLException {
		Connection con = getConnection(); 
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(query);
		return rs; 
	}
	

}