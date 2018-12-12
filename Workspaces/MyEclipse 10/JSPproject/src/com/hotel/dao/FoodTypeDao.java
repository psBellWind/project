package com.hotel.dao;

import java.util.List;

import com.hotal.entity.FoodType;

public interface FoodTypeDao {
	//添加菜系
	public void addFoodType(String typeName);
	//查询菜系
	public List<FoodType> findFoodTypes();
	//删除菜系
	public void deleteFoodType(int id);
	//修改菜系
	public void updateType(int id,String typeName);
	//按名字查询菜系
	public List<FoodType> findTypeByName(String typeName);
}
