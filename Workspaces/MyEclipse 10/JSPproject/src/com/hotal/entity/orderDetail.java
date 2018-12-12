package com.hotal.entity;
//订单明细表
public class orderDetail {
	private int id;
	private int orderId;//引入订单表的主键
	private int food_id;//菜单信息表的主键
	private int table_id;
	private int foodCount;
	private String foodName;//菜名
	private Double foodPrice;//单价
	private Double foodMrice;//会员价
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(Double foodPrice) {
		this.foodPrice = foodPrice;
	}
	public Double getFoodMrice() {
		return foodMrice;
	}
	public void setFoodMrice(Double foodMrice) {
		this.foodMrice = foodMrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
}
