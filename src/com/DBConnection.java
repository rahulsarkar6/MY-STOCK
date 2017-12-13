package com;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection implements Serializable{
	
	public static String SERVER = "localhost";
	public static int PORT = 3306;
	public static String DB = "se2";
	public static String USER = "root";
	public static String PASSWORD = "admin";
	public Connection connection;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public static Connection createConnection()
	{
		Connection connection = null;
			try 
			{
				//System.out.println("Inside Connection");
				com.mysql.jdbc.jdbc2.optional.MysqlDataSource db = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
				db.setServerName(SERVER);
				db.setPort(PORT);
				db.setDatabaseName(DB);
				db.setUser(USER);
				db.setPassword(PASSWORD);

				connection = db.getConnection();
				
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			} 
			return connection;
	}
	
}