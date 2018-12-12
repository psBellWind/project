package com.hotel.dao;

import java.sql.ResultSet;
import java.util.List;

import com.hotal.entity.Food;

public interface FoodDao {
	//添加菜品
	public void addFood(Food food);
	//查询指定菜品
	public Food findFood(int id);
	//查询指定菜品通过菜系名字
	public List<Food> findFood(String typeName);
	//查询所有菜品
	public List<Food> findAllFood();
	//删除菜品
	public void deleteFood(int id);
	//修改菜品
	public void updateFood(Food food);
	//查询指定菜品通过菜de名字
	public List<Food> selectFood(String foodName);
	
}
