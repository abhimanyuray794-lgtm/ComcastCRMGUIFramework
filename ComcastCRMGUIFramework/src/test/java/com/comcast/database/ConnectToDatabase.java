package com.comcast.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;

public class ConnectToDatabase {

	public static void main(String[] args) throws SQLException {
		//Step1: create object
		DataBaseUtility dFlib = new DataBaseUtility();
		
		//Step2: connect to database
		dFlib.getDbconnection();
		System.out.println("====done====");
		
		//step3: create statement and execute query
		ResultSet result = dFlib.executeSelectQuery("select * from project");
		while(result.next()) {
			System.out.println(result.getString(1));
		}
		
		//step4: close the connection
		dFlib.closeDbconnection();
		System.out.println("====close====");
		

	}

}
