package com.amazon.bean;

public class ProductCategory {
	private int hpc_id;
	private String hpc_name;
	private int hpc_paren_id;
	
	@Override
	public String toString() {
		return "ProductCategory [hpc_id=" + hpc_id + ", hpc_name=" + hpc_name + ", hpc_paren_id=" + hpc_paren_id + "]";
	}

	public ProductCategory(int hpc_id, String hpc_name, int hpc_paren_id) {
		super();
		this.hpc_id = hpc_id;
		this.hpc_name = hpc_name;
		this.hpc_paren_id = hpc_paren_id;
	}
	
	public ProductCategory(String hpc_name, int hpc_paren_id) {
		super();
		this.hpc_name = hpc_name;
		this.hpc_paren_id = hpc_paren_id;
	}

	public ProductCategory() {
		super();
	}

	public int getHpc_id() {
		return hpc_id;
	}

	public void setHpc_id(int hpc_id) {
		this.hpc_id = hpc_id;
	}

	public String getHpc_name() {
		return hpc_name;
	}

	public void setHpc_name(String hpc_name) {
		this.hpc_name = hpc_name;
	}

	public int getHpc_paren_id() {
		return hpc_paren_id;
	}

	public void setHpc_paren_id(int hpc_paren_id) {
		this.hpc_paren_id = hpc_paren_id;
	}
	
	
	
	
}
