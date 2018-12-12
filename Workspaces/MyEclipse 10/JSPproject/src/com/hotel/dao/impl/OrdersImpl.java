package com.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotal.MyJunit.MyJunit;
import com.hotal.entity.Orders;
import com.hotel.dao.OrdersDao;

public class OrdersImpl extends MyJunit implements OrdersDao {
	private Connection conn=null;
	private String sql=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Orders order=null;
	private List<Orders> orders=null;
	Date date=new Date();
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override//添加订单
	public void addOrder(Orders order) {
		conn=getConn();
		sql="insert into orders(table_id,orderDate,totalPrice,orderStatus,id) values(?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, order.getTable_id());
			ps.setString(2,order.getOrderDate());
			ps.setDouble(3,order.getTotalPrice());
			ps.setInt(4,order.getOrderStatus());
			ps.setInt(5, order.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}

	@Override//查询所有订单
	public List<Orders> findAllOrders() {
		orders=new ArrayList<Orders>();
		conn=getConn();
		sql="select o.id AS 'order_id',orderDate,table_id,totalPrice,orderStatus,tableName from orders o,dinnertable d where o.table_id=d.id ";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next()) {
				order=new Orders();
				order.setId(rs.getInt("order_id"));
				order.setOrderDate("orderDate");
				order.setTable_id(rs.getInt("table_id"));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				order.setOrderStatus(rs.getInt("orderStatus"));
				order.setTable_name(rs.getString("tableName"));
				orders.add(order);
			}
			return orders;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
		return null;
	}

	@Override//结账
	public void updateOrder(int id,double totalPrice) {
		conn=getConn();
		sql="update orders set orderStatus=?,totalPrice=? where id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,1);
			ps.setDouble(2, totalPrice);
			ps.setInt(3,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
		
		
	}

	@Override
	public void deleteOrder(int id) {
		conn=getConn();
		sql="delete from orders where id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}

}
