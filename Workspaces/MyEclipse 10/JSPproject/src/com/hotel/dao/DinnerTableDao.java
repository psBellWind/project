package com.hotel.dao;

import java.util.List;

import com.hotal.entity.DinnerTable;

public interface DinnerTableDao {
	//添加餐桌
	public void updateAdd(String tableName);
	//查询所有餐桌
	public List<DinnerTable> findAllTable();
	//查询未预定餐桌
	public List<DinnerTable> findTable(int tableStatu);
	//修改餐桌状态
	public void updateTable(int id,int tableStatus);
	//删除餐桌
	public void deletTable(int id);
	//查询餐桌
	public List<DinnerTable> findByName(String tableName);
}
