package com.hotel.dao;

import java.util.List;

import com.hotal.entity.Orders;


public interface OrdersDao {
	//添加订单
	public void addOrder(Orders order);
	//查询所有订单
	public List<Orders> findAllOrders();
	//修改订单状态
	public void updateOrder(int id,double totalPrice);
	//删除订单
	public void deleteOrder(int id);
}
