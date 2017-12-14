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
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
    private String emailid;
    private String username;
    private String password;
    private String uid;
    private String mid;
    private Double balance;
    
    
    
    public Double getBalance() {
		return balance;
	}







	public void setBalance(Double balance) {
		this.balance = balance;
	}







	public String getMid() {
		return mid;
	}







	public void setMid(String mid) {
		this.mid = mid;
	}







	public String getUid() {
		return uid;
	}







	public void setUid(String uid) {
		this.uid = uid;
	}




	private String output;
    
    
    public void viewprofile() {
    	output ="Name:" +name;
    }
    
    
    
   



    // public String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	//public String DB_URL="jdbc:mysql://localhost:3306/SE2";
	//public String USER = "root";
	//public String PASS = "admin";

    public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

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
    
    public String register() {
    	

	
    	Connection con = DBConnection.createConnection();
    	try {
    		

    		
//    		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//			ds.setServerName(System.getenv("ICSI518_SERVER"));
//			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
//			ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
//			ds.setUser(System.getenv("ICSI518_USER").toString());
//			ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
    	
//			con = ds.getConnection();
    		
    		String sql = "insert into users(name,emailid,username,password) values(?,?,?,?)";
			

			// Get a prepared SQL statement
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.name);
			st.setString(2, this.emailid);
			st.setString(3, this.username);
			st.setString(4, this.password);
			
			// Execute the statement
			st.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registration Successful",
							"Please enter username and Password to login"));
			return "Login";
			
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return "Login";
		
    }
    
    public String validateUsernamePassword() {
    	
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
    		
			String sql = "SELECT * from users where username = ? and password = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.username);
    		st.setString(2, this.password);
    		
    		
    		ResultSet rs = st.executeQuery();
    		
    		if (rs.next()) {
				System.out.println("Name is: " + rs.getString("name"));
				this.name = rs.getString("name");
				System.out.println("UID is: " + rs.getString("uid"));
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getString("uid"));
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
				//HttpSession session = SessionUtils.getSession();
				//session.setAttribute("username", username);
				 // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
	             // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid", rs.getString("uid"));
				return "welcome";
    		}
    		
    		else {
    			FacesContext.getCurrentInstance().addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_WARN,
    							"Please check your Username and Passowrd",
    							"Please enter correct username and Password"));
    			return "login";
    		}
    		
    		
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return "login";
   
    }
 
    

 public String updatedetails() {
    	
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
    		
			String sql = "Update users set name=?, emailid=?, password=? where username = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.name);
    		st.setString(2, this.emailid);
    		st.setString(3, this.password);
    		st.setString(4, this.username);
    		
    		st.executeUpdate();
    		FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Update Successful",
							"Please enter username and Password to login"));
			return "profile";
    	}
    		catch (Exception e) {
    			System.err.println("Exception: " + e.getMessage());
    		}
		return "profile";
   }




	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "Login";
	}



public List<Manager> managerdetails() {
    	
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
    		
			String sql = "Select * from manager";
			PreparedStatement st = con.prepareStatement(sql);
			List<Manager> list = new ArrayList<Manager>();
			Manager u;
    		ResultSet rs = st.executeQuery(sql);
    		while (rs.next()) {
    			System.out.println("Inside while loop");
				u = new Manager();
				u.setName(rs.getString("name"));
				System.out.println(u.getName());
				u.setUsername(rs.getString("username"));
				u.setEmailid(rs.getString("emailid"));
				list.add(u);
				
			}
    		return list;
//    		FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO,
//							"Update Successful",
//							"Please enter username and Password to login"));
//			//return "profile";
    	}
    		catch (Exception e) {
    			System.err.println("Exception: " + e.getMessage());
    		}
		//return "profile";
		return null;
   }




	@Override
	public String toString() {
		return "User [name=" + name + ", uid="+ uid+" ,emailid =" + emailid + ", username=" + username + ", password=" + password + "]";
	}

public String assignmanager() {
    	
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
    		
			String sql = "Update users set mid=? where username = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.mid);
			st.setString(2, this.username);
    		
    		st.executeUpdate();
    		FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Manager assigned",
							"Please enter username and Password to login"));
			return "managerprofile";
    	}
    		catch (Exception e) {
    			System.err.println("Exception: " + e.getMessage());
    		}
		return "managerprofile";
   }

	public Double viewbalance() {
		Connection con = DBConnection.createConnection();
		Double balance = null;
		try {
			
			String sql = "Select balance from users where username = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.username);
			ResultSet rs = st.executeQuery();
			rs.next();
			 balance =  rs.getDouble("balance");
			
		}catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return balance;
	}
    
    
}