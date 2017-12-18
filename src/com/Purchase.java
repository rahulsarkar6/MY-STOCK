package com;

import java.lang.*;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import javax.faces.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import com.SessionUtils;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import javafx.scene.control.Alert;
        
@ManagedBean
@SessionScoped
public class Purchase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private String stock_symbol;
	private int qty;
	private Double price;
	private Double amt;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}

	private Date date;
	List<Purchase> mgr= new ArrayList<Purchase>();
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getStock_symbol() {
		return stock_symbol;
	}
	public void setStock_symbol(String stock_symbol) {
		this.stock_symbol = stock_symbol;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Purchase(int id, int uid, String stock_symbol, int qty, Double price, Double amt, Date date) {
		super();
		this.id = id;
		this.uid = uid;
		this.stock_symbol = stock_symbol;
		this.qty = qty;
		this.price = price;
		this.amt = amt;
		this.date = date;
	}
	
	public Purchase() {
		
	}
	
	public List<Purchase> getPurchase() throws SQLException {
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
		System.out.println("Hello in Purchase");
		Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("uid"));
		System.out.println(uid);
		
	String sql = "select * from purchase where uid = '"+ uid +"' ";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Purchase p=new Purchase();
		p.setAmt(rs.getDouble("amt"));
		p.setPrice(rs.getDouble("price"));
		p.setStock_symbol(rs.getString("stock_symbol"));
		p.setQty(rs.getInt("qty"));
		p.setDate(rs.getDate("date"));
		
		
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(p);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	public List<Purchase> getSell() throws SQLException {
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
		System.out.println("Hello in Sell");
		Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("uid"));
		System.out.println(uid);
		
	String sql = "select * from sell where uid = '"+ uid +"' ";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Purchase p=new Purchase();
		p.setAmt(rs.getDouble("amt"));
		p.setPrice(rs.getDouble("price"));
		p.setStock_symbol(rs.getString("stock_symbol"));
		p.setQty(rs.getInt("qty"));
		p.setDate(rs.getDate("date"));
		
		
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(p);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}

	
	public List<Purchase> getPurchasem() throws SQLException {
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
		System.out.println("Hello in Purchase");
		Integer m = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("mid"));
		System.out.println(m);
		
	String sql = "select * from purchase where mid = '"+ m +"' ";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Purchase p=new Purchase();
		p.setAmt(rs.getDouble("amt"));
		p.setPrice(rs.getDouble("price"));
		p.setStock_symbol(rs.getString("stock_symbol"));
		p.setQty(rs.getInt("qty"));
		p.setDate(rs.getDate("date"));
		p.setUid(rs.getInt("uid"));
		
		
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(p);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	
	public List<Purchase> getSellm() throws SQLException {
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
		System.out.println("Hello in Sell");
		Integer m = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("mid"));
		System.out.println(m);
		
	String sql = "select * from sell where mid = '"+ m +"' ";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Purchase p=new Purchase();
		p.setAmt(rs.getDouble("amt"));
		p.setPrice(rs.getDouble("price"));
		p.setStock_symbol(rs.getString("stock_symbol"));
		p.setQty(rs.getInt("qty"));
		p.setDate(rs.getDate("date"));
		p.setUid(rs.getInt("uid"));
		
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(p);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	public List<Purchase> getWatch() throws SQLException {
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
		System.out.println("Hello in Watchlist");
		Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("uid"));
		System.out.println(uid);
		
	String sql = "select distinct(stock_symbol) from watchlist where uid = '"+ uid +"' ";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Purchase p=new Purchase();
		
		p.setStock_symbol(rs.getString("stock_symbol"));
		
		
		
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(p);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	public List<Purchase> getWatch1() throws SQLException {
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
		System.out.println("Hello in Watchlist");
		Integer mid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("mid"));
		System.out.println(mid);
		
	String sql = "select distinct(stock_symbol) from watchlist where mid = '"+ mid +"' ";
	
	//PreparedStatement st = con.prepareStatement(sql);
	Statement stm=null;
	stm=(Statement) con.createStatement();
	
	
	
	
	ResultSet rs=stm.executeQuery(sql);
	while(rs.next())
	{
		Purchase p=new Purchase();
		
		p.setStock_symbol(rs.getString("stock_symbol"));
		
		
		
		//System.out.println(rs.getInt("id")+" " +rs.getString("name"));
		mgr.add(p);
		
	}
	con.close();
	stm.close();
	
	
	return mgr;
	}
	
	
	
}