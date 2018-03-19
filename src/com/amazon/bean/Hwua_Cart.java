package com.amazon.bean;

public class Hwua_Cart {
	//足迹
	//购物车id
	private int id;
	//商品ID
	private int pid;
	//当前数量
	private int quantity;
	//用户ID
	private int userid;
	
	private HwuaProduct hwuaProduct;
	
	@Override
	public String toString() {
		return "Hwua_Cart [id=" + id + ", pid=" + pid + ", quantity=" + quantity + ", userid=" + userid + "]";
	}

	public HwuaProduct getHwuaProduct() {
		return hwuaProduct;
	}

	public void setHwuaProduct(HwuaProduct hwuaProduct) {
		this.hwuaProduct = hwuaProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Hwua_Cart() {
		super();
	}

	public Hwua_Cart(int pid, int quantity, int userid) {
		super();
		this.pid = pid;
		this.quantity = quantity;
		this.userid = userid;
	}

	public Hwua_Cart(int id, int pid, int quantity, int userid) {
		super();
		this.id = id;
		this.pid = pid;
		this.quantity = quantity;
		this.userid = userid;
	}
	
	
}
