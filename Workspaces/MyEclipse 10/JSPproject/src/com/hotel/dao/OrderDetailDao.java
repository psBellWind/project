package com.hotel.dao;

import java.util.List;

import com.hotal.entity.orderDetail;

public interface OrderDetailDao {
	public List<orderDetail> findOrderDetails(int id);
	public void addOrderDetail(orderDetail orderDetail);
	//查询指定菜的订单详情
	public List<orderDetail> findOrderDetailsByOrderId(int orderId,int orderStatus,int foodId);
	//查询指定订单的详情
	public List<orderDetail> findOrderDetailsByOrderId(int orderId,int orderStatus);
	//修改
	public void updateByOrderId(orderDetail orderDetail);
	//删除
	public void deleteOrder(int id);
	
}
