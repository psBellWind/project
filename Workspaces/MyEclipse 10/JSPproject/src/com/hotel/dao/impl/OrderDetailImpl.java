package com.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotal.MyJunit.MyJunit;
import com.hotal.entity.Orders;
import com.hotal.entity.orderDetail;
import com.hotel.dao.OrderDetailDao;

public class OrderDetailImpl extends MyJunit implements OrderDetailDao {
	private Connection conn=null;
	private String sql=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private orderDetail orderDetail=null;
	private List<orderDetail> orderDetails=null;
	@Override//查询订单详情
	public List<orderDetail> findOrderDetails(int id) {
		orderDetails=new ArrayList<orderDetail>();
		
		conn=getConn();
		sql="select od.id AS'odid',o.id AS 'oid',food_id,foodName,foodCount,price,mprice from orders o,orderDetail od,food f where od.id=? and f.id=food_id";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,id);
			rs=ps.executeQuery();
			while (rs.next()) {
				orderDetail=new orderDetail();
				orderDetail.setId(rs.getInt("odid"));
				orderDetail.setOrderId(rs.getInt("oid"));
				orderDetail.setFood_id(rs.getInt("food_id"));
				orderDetail.setFoodCount(rs.getInt("foodCount"));
				orderDetail.setFoodName(rs.getString("foodName"));
				orderDetail.setFoodPrice(rs.getDouble("price"));
				orderDetail.setFoodMrice(rs.getDouble("mprice"));
				orderDetails.add(orderDetail);
			}
			return orderDetails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
		return null;
	}

	@Override//添加订单
	public void addOrderDetail(orderDetail orderDetail) {
		
		conn=getConn();
		sql="insert into orderDetail(orderId,food_id,foodCount) values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, orderDetail.getOrderId());
			ps.setInt(2, orderDetail.getFood_id());
			ps.setInt(3, orderDetail.getFoodCount());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}

	}

	@Override//通过订单id查询订单详情
	public List<orderDetail> findOrderDetailsByOrderId(int orderId,int orderStatus,int foodId) {
		orderDetails=new ArrayList<orderDetail>();
		
		conn=getConn();
		sql="select od.id AS'odid',o.id AS 'oid',food_id,foodName,foodCount,price,mprice from orders o,orderDetail od,food f,dinnerTable t where o.id=? and food_id=? and o.table_id=t.id and o.orderStatus=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,orderId);
			ps.setInt(2,orderStatus);
			ps.setInt(3, foodId);
			rs=ps.executeQuery();
			while (rs.next()) {
				orderDetail=new orderDetail();
				orderDetail.setId(rs.getInt("odid"));
				orderDetail.setOrderId(orderId);
				
				orderDetail.setFood_id(rs.getInt("food_id"));
				orderDetail.setFoodCount(rs.getInt("foodCount"));
				orderDetail.setFoodName(rs.getString("foodName"));
				orderDetail.setFoodPrice(rs.getDouble("price"));
				orderDetail.setFoodMrice(rs.getDouble("mprice"));
				orderDetails.add(orderDetail);
			}
			return orderDetails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
		return null;
	}

	@Override//修改信息
	public void updateByOrderId(orderDetail orderDetail) {
		conn=getConn();
		sql="update orderDetail foodCount=? where id=?";
		try {
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, orderDetail.getFoodCount());
			ps.setInt(2, orderDetail.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
		
	}

	@Override
	public List<orderDetail> findOrderDetailsByOrderId(int orderId, int orderStatus) {
		orderDetails=new ArrayList<orderDetail>();
		
		conn=getConn();
		sql="select od.id AS 'oid',food_id,foodName,foodCount,price,mprice from orders o,orderDetail od,food f,dinnerTable t where o.id=? and o.id=orderId and food_id=f.id and o.table_id=t.id and o.orderStatus=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,orderId);
			ps.setInt(2,orderStatus);
			
			rs=ps.executeQuery();
			while (rs.next()) {
				orderDetail=new orderDetail();
				orderDetail.setId(rs.getInt("oid"));
				orderDetail.setOrderId(orderId);
				
				orderDetail.setFood_id(rs.getInt("food_id"));
				orderDetail.setFoodCount(rs.getInt("foodCount"));
				orderDetail.setFoodName(rs.getString("foodName"));
				
				orderDetail.setFoodPrice(rs.getDouble("price"));
				orderDetail.setFoodMrice(rs.getDouble("mprice"));
				orderDetails.add(orderDetail);
			}
			return orderDetails;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		return null;
	}

	@Override//删除详情
	public void deleteOrder(int id) {
		conn=getConn();
		sql="delete from orderDetail where id=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
		
	}

}
