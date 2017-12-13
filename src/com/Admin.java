package com;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import javax.faces.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import com.SessionUtils;
import com.DBConnection;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.bind.util.Which;

import javafx.scene.control.Alert;
        
@ManagedBean
@SessionScoped
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String username;
    private String password;
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
    
public String validate() {
    	
    	Connection con = DBConnection.createConnection();
    	try {
			
//    		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//			ds.setServerName(System.getenv("ICSI518_SERVER"));
//			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
//			ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
//			ds.setUser(System.getenv("ICSI518_USER").toString());
//			ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
//    		
//			con = ds.getConnection();
    		
			String sql = "SELECT * from admin where username = ? and password = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.username);
    		st.setString(2, this.password);
    		
    		
    		ResultSet rs = st.executeQuery();
    		
    		if (rs.next()) {
				System.out.println("Name is: " + rs.getString("username"));
				this.username = rs.getString("username");
				
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
				//HttpSession session = SessionUtils.getSession();
				//session.setAttribute("username", username);
				 // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
	             // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getString("uid"));
				return "adminhome";
    		}
    		
    		else {
    			FacesContext.getCurrentInstance().addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_WARN,
    							"Please check your Username and Passowrd",
    							"Please enter correct username and Password"));
    			return "admin";
    		}
    		
    		
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return "admin";
   
    }
 
public String logout() {
	HttpSession session = SessionUtils.getSession();
	session.invalidate();
	return "admin";
}
    
}
