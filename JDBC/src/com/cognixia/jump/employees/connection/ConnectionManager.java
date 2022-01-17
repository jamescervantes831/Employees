package com.cognixia.jump.employees.connection;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
	
	private static Connection connection = null;
	
	private static void makeConnection() {
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream("resources/config.properties"));
			
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			System.out.println(url);
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection was made");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Could not make connection");
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			makeConnection();
		}
		
		return connection;
	}
}
