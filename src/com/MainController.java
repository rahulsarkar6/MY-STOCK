package com;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name="main")
@SessionScoped
public class MainController implements Serializable {
	
	private Manager manager = new Manager();
	ListQuery query = new ListQuery();
	List<Manager> list = new ArrayList<Manager>();
	
	public List<Manager> getList() {
		list = query.listManager();
		return list;
	}
	public void setList(List<Manager> list) {
		this.list = list;
	}
	
	
	
}