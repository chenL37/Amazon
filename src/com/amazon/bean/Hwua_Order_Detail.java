package com.amazon.bean;

public class Hwua_Order_Detail {
	private int Hod_id;
	private int Ho_id;
	private int Hp_id;
	private int HodQuantity_Number;
	private float Hod_cost;
	private HwuaProduct product;
	@Override
	public String toString() {
		return "Hwua_Order_Detail [Hod_id=" + Hod_id + ", Ho_id=" + Ho_id + ", Hp_id=" + Hp_id + ", HodQuantity_Number="
				+ HodQuantity_Number + ", Hod_cost=" + Hod_cost + "]";
	}
	public HwuaProduct getProduct() {
		return product;
	}
	public void setProduct(HwuaProduct product) {
		this.product = product;
	}
	public int getHod_id() {
		return Hod_id;
	}
	public void setHod_id(int hod_id) {
		Hod_id = hod_id;
	}
	public int getHo_id() {
		return Ho_id;
	}
	public void setHo_id(int ho_id) {
		Ho_id = ho_id;
	}
	public int getHp_id() {
		return Hp_id;
	}
	public void setHp_id(int hp_id) {
		Hp_id = hp_id;
	}
	public int getHodQuantity_Number() {
		return HodQuantity_Number;
	}
	public void setHodQuantity_Number(int hodQuantity_Number) {
		HodQuantity_Number = hodQuantity_Number;
	}
	public float getHod_cost() {
		return Hod_cost;
	}
	public void setHod_cost(float hod_cost) {
		Hod_cost = hod_cost;
	}
	public Hwua_Order_Detail(int hod_id, int ho_id, int hp_id, int hodQuantity_Number, float hod_cost) {
		super();
		Hod_id = hod_id;
		Ho_id = ho_id;
		Hp_id = hp_id;
		HodQuantity_Number = hodQuantity_Number;
		Hod_cost = hod_cost;
	}
	public Hwua_Order_Detail(int ho_id, int hp_id, int hodQuantity_Number, float hod_cost) {
		super();
		Ho_id = ho_id;
		Hp_id = hp_id;
		HodQuantity_Number = hodQuantity_Number;
		Hod_cost = hod_cost;
	}
	public Hwua_Order_Detail() {
		super();
	}
}
