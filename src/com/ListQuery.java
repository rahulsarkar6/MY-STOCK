package com;
import com.DBConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ListQuery implements Serializable {
	
	
	 public List<Manager> listManager(){
		List<Manager> list = new ArrayList<Manager>();
		Manager u;
		 try {
			
			 Connection conn = DBConnection.createConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("Select * from manager");
				
				
				
				
				while(rs.next()) {
					System.out.println("Inside while loop");
					u = new Manager();
					u.setName(rs.getString("name"));
					System.out.println(u.getName());
					u.setUsername(rs.getString("username"));
					u.setEmail(rs.getString("email"));
					list.add(u);
					
				}
			 return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		 
		 
	 }
	
}

