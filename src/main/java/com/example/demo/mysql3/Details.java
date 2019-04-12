package com.example.demo.mysql3;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "details")
public class Details {

	@Id
	private int id;
	private String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Details(int id, String address) {
		super();
		this.id = id;
		this.address = address;
	}
	public Details()
	{
		
	}
}
