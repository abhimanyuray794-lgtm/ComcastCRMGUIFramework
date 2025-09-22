package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection con;
	
//	step1: connect to database
	public void getDbconnection(String url, String username, String password) {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			con= DriverManager.getConnection(url, username, password);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//					or
	public void getDbconnection() {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			con= DriverManager.getConnection("jdbc:mysql://49.249.28.218:3307/projects", "root@%", "root");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	step2: create statement and execute query
	public ResultSet executeSelectQuery(String query) {
		ResultSet result = null;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	step3: create statement and execute non select query
	public int executeNonSelectQuery(String query) {
		int result = 0;
		try {
			Statement stat = con.createStatement();
			result = stat.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	step4: close database
	public void closeDbconnection() {
		try {
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
