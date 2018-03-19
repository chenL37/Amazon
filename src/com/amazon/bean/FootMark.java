package com.amazon.bean;

import java.sql.Date;

public class FootMark {
	private int footMark_id ;
	private int hpc_id;
	private int user_id;
	private Date footMark_date;
	
	@Override
	public String toString() {
		return "FootMark [id=" + footMark_id + ", hpc_id=" + hpc_id + ", user_id=" + user_id + ", date=" + footMark_date + "]";
	}
	public int getFootMark_id() {
		return footMark_id;
	}
	public void setFootMark_id(int id) {
		this.footMark_id = id;
	}
	public int getHpc_id() {
		return hpc_id;
	}
	public void setHpc_id(int hpc_id) {
		this.hpc_id = hpc_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getFootMark_Date() {
		return footMark_date;
	}
	public void setFootMark_Date(Date date) {
		this.footMark_date = date;
	}
	public FootMark(int id, int hpc_id, int user_id, Date date) {
		super();
		this.footMark_id = id;
		this.hpc_id = hpc_id;
		this.user_id = user_id;
		this.footMark_date = date;
	}
	public FootMark(int hpc_id, int user_id, Date date) {
		super();
		this.hpc_id = hpc_id;
		this.user_id = user_id;
		this.footMark_date = date;
	}
	public FootMark() {
		super();
	}
	
}
