package com.amazon.bean;

import java.sql.Date;

public class Hwua_Order {
	private int Ho_Id;
	private int Ho_User_Id;
	private String Ho_User_Name;
	private String Ho_User_Address;
	private Date Ho_Create_Time;
	private float Ho_cost;
	private int Ho_Status;
	private int Ho_Type;
	@Override
	public String toString() {
		return "Hwua_Order [Ho_Id=" + Ho_Id + ", Ho_User_Id=" + Ho_User_Id + ", Ho_User_Name=" + Ho_User_Name
				+ ", Ho_User_Address=" + Ho_User_Address + ", Ho_Create_Time=" + Ho_Create_Time + ", Ho_cost=" + Ho_cost
				+ ", Ho_Status=" + Ho_Status + ", Ho_Type=" + Ho_Type + "]";
	}
	public int getHo_Id() {
		return Ho_Id;
	}
	public void setHo_Id(int ho_Id) {
		Ho_Id = ho_Id;
	}
	public int getHo_User_Id() {
		return Ho_User_Id;
	}
	public void setHo_User_Id(int ho_User_Id) {
		Ho_User_Id = ho_User_Id;
	}
	public String getHo_User_Name() {
		return Ho_User_Name;
	}
	public void setHo_User_Name(String ho_User_Name) {
		Ho_User_Name = ho_User_Name;
	}
	public String getHo_User_Address() {
		return Ho_User_Address;
	}
	public void setHo_User_Address(String ho_User_Address) {
		Ho_User_Address = ho_User_Address;
	}
	public Date getHo_Create_Time() {
		return Ho_Create_Time;
	}
	public void setHo_Create_Time(Date ho_Create_Time) {
		Ho_Create_Time = ho_Create_Time;
	}
	public float getHo_cost() {
		return Ho_cost;
	}
	public void setHo_cost(float ho_cost) {
		Ho_cost = ho_cost;
	}
	public int getHo_Status() {
		return Ho_Status;
	}
	public void setHo_Status(int ho_Status) {
		Ho_Status = ho_Status;
	}
	public int getHo_Type() {
		return Ho_Type;
	}
	public void setHo_Type(int ho_Type) {
		Ho_Type = ho_Type;
	}
	public Hwua_Order() {
		super();
	}
	public Hwua_Order(int ho_User_Id, String ho_User_Name, String ho_User_Address, Date ho_Create_Time, float ho_cost,
			int ho_Status, int ho_Type) {
		super();
		Ho_User_Id = ho_User_Id;
		Ho_User_Name = ho_User_Name;
		Ho_User_Address = ho_User_Address;
		Ho_Create_Time = ho_Create_Time;
		Ho_cost = ho_cost;
		Ho_Status = ho_Status;
		Ho_Type = ho_Type;
	}
	public Hwua_Order(int ho_Id, int ho_User_Id, String ho_User_Name, String ho_User_Address, Date ho_Create_Time,
			float ho_cost, int ho_Status, int ho_Type) {
		super();
		Ho_Id = ho_Id;
		Ho_User_Id = ho_User_Id;
		Ho_User_Name = ho_User_Name;
		Ho_User_Address = ho_User_Address;
		Ho_Create_Time = ho_Create_Time;
		Ho_cost = ho_cost;
		Ho_Status = ho_Status;
		Ho_Type = ho_Type;
	}
}
