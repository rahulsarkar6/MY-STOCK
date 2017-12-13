package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.User;


@ManagedBean
@SessionScoped
public class UserController{
	
	public List<User> listuser(){
		
		List<User> list= new ArrayList<User>();
		Connection con = null;
		try {
			
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName(System.getenv("ICSI518_SERVER"));
			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
			ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
			ds.setUser(System.getenv("ICSI518_USER").toString());
			ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
    		
			con = ds.getConnection();
    		
			String sql = "SELECT * from users";
			PreparedStatement st = con.prepareStatement(sql);
			User u;
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				u = new User();
				u.setName(rs.getString("name"));
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