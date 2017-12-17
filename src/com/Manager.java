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

import com.*;
import javax.faces.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import javafx.scene.control.Alert;
        
@ManagedBean
@SessionScoped
public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
    private String emailid;
    private String username;
    private String password;
    private String company;
    private int mid;
    private int status;
    private int fees;
    
    List<Manager> mgr= new ArrayList<Manager>();
    List<User> user = new ArrayList<User>();
    List<User> user1 = new ArrayList<User>();
   


	private Manager selectedManager = null;
    
   // public String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	//public String DB_URL="jdbc:mysql://localhost:3306/SE2";
	//public String USER = "root";
	//public String PASS = "admin";

    
    
    
    
    public Manager getSelectedManager() {
		return selectedManager;
	}



	public int getFees() {
		return fees;
	}



	public void setFees(int fees) {
		this.fees = fees;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public Manager() {
	
}



	public Manager(String name, String emailid, String username, String password, String company, int mid, int fees) {
	super();
	this.name = name;
	this.emailid = emailid;
	this.username = username;
	this.password = password;
	this.company = company;
	this.mid = mid;
	this.fees = fees;
}



	public void setSelectedManager(Manager selectedManager) {
		this.selectedManager = selectedManager;
	}



	public String getCompany() {
		return company;
	}

	

	public int getMid() {
		return mid;
	}



	public void setMid(int mid) {
		this.mid = mid;
	}



	



	public void setCompany(String company) {
		this.company = company;
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
//    	
//			con = ds.getConnection();
    		
    		String sql = "insert into manager(name,emailid,username,password,company,fees) values(?,?,?,?,?,?)";
			

			// Get a prepared SQL statement
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.name);
			st.setString(2, this.emailid);
			st.setString(3, this.username);
			st.setString(4, this.password);
			st.setString(5, this.company);
			st.setInt(6, this.fees);
			// Execute the statement
			st.executeUpdate();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registration Successful",
							"Please enter username and Password to login"));
			return "Loginmanager";
			
			
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return "Loginmanager";
		
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
    		
			String sql = "SELECT * from manager where username = ? and password = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.username);
    		st.setString(2, this.password);
    		
    		
    		ResultSet rs = st.executeQuery();
    		
    		if (rs.next()) {
				System.out.println("Name is: " + rs.getString("name"));
				this.name = rs.getString("name");
				//HttpSession session = SessionUtils.getSession();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mid", rs.getString("mid"));
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", rs.getString("username"));
				//session.setAttribute("username", username);
				return "managerdashboard";
    		}
    		
    		else {
    			FacesContext.getCurrentInstance().addMessage(
    					null,
    					new FacesMessage(FacesMessage.SEVERITY_WARN,
    							"Please check your Username and Passowrd",
    							"Please enter correct username and Password"));
    			return "loginmanager";
    		}
    		
    		
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		return "loginmanager";
   
    }
 
    public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}
    
    
    public List<Manager> getMang() throws SQLException {
		mgr.clear();

		Connection con = DBConnection.createConnection();
//		Connection con = null;
//		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//		ds.setServerName(System.getenv("ICSI518_SERVER"));
//		ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
//		ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
//		ds.setUser(System.getenv("ICSI518_USER").toString());
//		ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
//		
//		
//		con = ds.getConnection();

	
	
	
	
	//con = ds.getConnection();

	
	String sql = "select * from manager where status = 1";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Manager m=new Manager();
		m.setName(rs.getString("name"));
		
		m.setEmailid(rs.getString("emailid"));
		m.setCompany(rs.getString("company"));
		m.setMid(rs.getInt("mid"));
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(m);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
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
    		
			String sql = "Update manager set name=?, emailid=?, password=? where username = ?";
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
			return "managerprofileupdate";
    	}
    		catch (Exception e) {
    			System.err.println("Exception: " + e.getMessage());
    		}
		return "managerprofileupdate";
   }
    
    public void select(Manager m) {
    	
    	System.out.println("Hi");
    	System.out.println(m.toString());
    	selectedManager = m;
    	Connection con = DBConnection.createConnection();
    	try {
    		System.out.println("Hello");
			String sql = "Update manager set status= ? where mid = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, 1);
			st.setInt(2, m.mid);
    		System.out.println(m.mid);
    		st.executeUpdate();
    		FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Manager Approved",
							"Please enter username and Password to login"));
			//return "managerrequest";
    	}catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
	//return "managerrequest";

    }

//public String approvemanager() {
//    	
//		//System.out.println(m.toString());
//		selectedManager = new Manager();
//	 	Connection con = DBConnection.createConnection();
//	 	//selectedManager = m;
//    	try {
//			
////    		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
////			ds.setServerName(System.getenv("ICSI518_SERVER"));
////			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
////			ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
////			ds.setUser(System.getenv("ICSI518_USER").toString());
////			ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
////    		
////			con = ds.getConnection();
//    		System.out.println("Hello");
//			String sql = "Update manager set status= ? where mid = ?";
//			PreparedStatement st = con.prepareStatement(sql);
//			st.setInt(1, 1);
//			st.setInt(2, this.mid);
//    		System.out.println(this.mid);
//    		st.executeUpdate();
//    		FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO,
//							"Manager Approved",
//							"Please enter username and Password to login"));
//			return "managerrequest";
//    	}
//    		catch (Exception e) {
//    			System.err.println("Exception: " + e.getMessage());
//    		}
//		return "managerrequest";
//   }

	@Override
	public String toString() {
		return "Manager [name=" + name + ", emailid=" + emailid + ", username=" + username + ", password=" + password
				+ ", company=" + company + ", mid=" + mid + "]";
	}
    
	public List<Manager> getManage() throws SQLException {
		mgr.clear();

		Connection con = DBConnection.createConnection();
//		Connection con = null;
//		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//		ds.setServerName(System.getenv("ICSI518_SERVER"));
//		ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
//		ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
//		ds.setUser(System.getenv("ICSI518_USER").toString());
//		ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
//		
//		
//		con = ds.getConnection();

	
	
	
	
	//con = ds.getConnection();
		System.out.println("Hello in manage");
	
	String sql = "select * from manager where status = 0";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Manager m=new Manager();
		m.setName(rs.getString("name"));
		
		m.setEmailid(rs.getString("emailid"));
		m.setCompany(rs.getString("company"));
		m.setMid(rs.getInt("mid"));
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(m);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	public List<Manager> getShowmanager() throws SQLException {
		mgr.clear();

		Connection con = DBConnection.createConnection();
//		Connection con = null;
//		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//		ds.setServerName(System.getenv("ICSI518_SERVER"));
//		ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
//		ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
//		ds.setUser(System.getenv("ICSI518_USER").toString());
//		ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
//		
//		
//		con = ds.getConnection();


		System.out.println("Hello in showmanager");
		Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("uid"));
	String sql = "select * from manager where mid = (select mid from users where uid = '"+ uid +"')";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Manager m=new Manager();
		m.setName(rs.getString("name"));
		m.setEmailid(rs.getString("emailid"));
		m.setCompany(rs.getString("company"));
		m.setMid(rs.getInt("mid"));
		m.setFees(rs.getInt("fees"));
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(m);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	 public List<User> getUser() throws SQLException {
	    	Connection con = null;
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName(System.getenv("ICSI518_SERVER"));
			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
			ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
			ds.setUser(System.getenv("ICSI518_USER").toString());
			ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
			
			
			con = ds.getConnection();


			System.out.println("Hello in showrequest");
			
			
			Integer m = Integer.parseInt((String) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("mid"));
			
			System.out.println(m);
			
			String sql = "select * from request where mid = '"+ m +"' and flag = 0";
		
			//PreparedStatement st = con.prepareStatement(sql);
			Statement stm=null;
			stm=(Statement) con.createStatement();
		
		
		
		
			ResultSet rs=stm.executeQuery(sql);
			user.clear();
			while(rs.next())
			{
				User u=new User();
				u.setUid(rs.getString("uid"));
				u.setQty(rs.getInt("qty"));
				
				
				//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
				user.add(u);
				
			}
			con.close();
			stm.close();
			
			
			return user;
			}



	public List<User> getUser1() throws SQLException{
		
		
		Connection con = null;
		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
		ds.setServerName(System.getenv("ICSI518_SERVER"));
		ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
		ds.setDatabaseName(System.getenv("ICSI518_DB").toString());
		ds.setUser(System.getenv("ICSI518_USER").toString());
		ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());
		
		
		con = ds.getConnection();


		System.out.println("Hello in showrequest");
		
		
		Integer m = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("mid"));
		
		System.out.println(m);
		
		String sql = "select * from requestsell where mid = '"+ m +"' and flag = 0";
	
		//PreparedStatement st = con.prepareStatement(sql);
		Statement stm=null;
		stm=(Statement) con.createStatement();
	
	
	
	
		ResultSet rs=stm.executeQuery(sql);
		user1.clear();
		while(rs.next())
		{
			User u=new User();
			u.setUid(rs.getString("uid"));
			u.setQty(rs.getInt("qty"));
			
			
			//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
			user1.add(u);
			
		}
		con.close();
		stm.close();
	
		
		
		return user1;
	}
	 
	 
	public Double viewbalance() {
		Connection con = DBConnection.createConnection();
		Double balance = 0.0;
		try {
			
			String sql = "Select accountbalance from manager where username = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, this.username);
			ResultSet rs = st.executeQuery();
			rs.next();
			 balance =  rs.getDouble("accountbalance");
			
		}catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return balance;
	}
	 
	 
	    
}

	
		
