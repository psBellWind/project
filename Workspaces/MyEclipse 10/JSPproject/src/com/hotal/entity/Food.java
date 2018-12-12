package com.hotal.entity;
//菜品类
public class Food {
	private int id;
	private String foodName;
	private int foodType_id;//所属菜系id
	private String typeName;//所属菜系名称
	private int foodTypeId;//所属菜系id,菜系表中
	private double price;
	private double mprice;//会员价格
	private String remark;//简介
	private String img;//图片
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodType_id() {
		return foodType_id;
	}
	public void setFoodType_id(int foodType_id) {
		this.foodType_id = foodType_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getMprice() {
		return mprice;
	}
	public void setMprice(double mprice) {
		this.mprice = mprice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	
	
}
