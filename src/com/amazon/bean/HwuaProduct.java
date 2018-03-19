package com.amazon.bean;

public class HwuaProduct {
	private int hp_Id;
	private String hp_Name;
	private String Hp_description;
	private int Hp_price;
	private int Hp_stock;
	private int Hpc_id;
	private int Hpc_child_id;
	private String Hp_file_name;
	@Override
	public String toString() {
		return "HwuaProduct [Hp_id=" + hp_Id + ", Hp_name=" + hp_Name + ", Hp_description=" + Hp_description
				+ ", Hp_price=" + Hp_price + ", Hp_stock=" + Hp_stock + ", Hpc_id=" + Hpc_id + ", Hpc_child_id="
				+ Hpc_child_id + ", Hp_file_name=" + Hp_file_name + "]";
	}
	public HwuaProduct() {
		super();
	}
	public HwuaProduct(String hp_name, String hp_description, int hp_price, int hp_stock, int hpc_id, int hpc_child_id,
			String hp_file_name) {
		super();
		hp_Name = hp_name;
		Hp_description = hp_description;
		Hp_price = hp_price;
		Hp_stock = hp_stock;
		Hpc_id = hpc_id;
		Hpc_child_id = hpc_child_id;
		Hp_file_name = hp_file_name;
	}
	public HwuaProduct(int hp_id, String hp_name, String hp_description, int hp_price, int hp_stock, int hpc_id,
			int hpc_child_id, String hp_file_name) {
		super();
		hp_Id = hp_id;
		hp_Name = hp_name;
		Hp_description = hp_description;
		Hp_price = hp_price;
		Hp_stock = hp_stock;
		Hpc_id = hpc_id;
		Hpc_child_id = hpc_child_id;
		Hp_file_name = hp_file_name;
	}
	public int gethp_Id() {
		return hp_Id;
	}
	public void sethp_Id(int hp_id) {
		hp_Id = hp_id;
	}
	public String gethp_Name() {
		return hp_Name;
	}
	public void sethp_Name(String hp_name) {
		hp_Name = hp_name;
	}
	public String getHp_description() {
		return Hp_description;
	}
	public void setHp_description(String hp_description) {
		Hp_description = hp_description;
	}
	public int getHp_price() {
		return Hp_price;
	}
	public void setHp_price(int hp_price) {
		Hp_price = hp_price;
	}
	public int getHp_stock() {
		return Hp_stock;
	}
	public void setHp_stock(int hp_stock) {
		Hp_stock = hp_stock;
	}
	public int getHpc_id() {
		return Hpc_id;
	}
	public void setHpc_id(int hpc_id) {
		Hpc_id = hpc_id;
	}
	public int getHpc_child_id() {
		return Hpc_child_id;
	}
	public void setHpc_child_id(int hpc_child_id) {
		Hpc_child_id = hpc_child_id;
	}
	public String getHp_file_name() {
		return Hp_file_name;
	}
	public void setHp_file_name(String hp_file_name) {
		Hp_file_name = hp_file_name;
	}
}
