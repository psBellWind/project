package com.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hotal.MyJunit.MyJunit;

import com.hotal.entity.FoodType;
import com.hotel.dao.FoodTypeDao;

public class FoodTypeImpl extends MyJunit implements FoodTypeDao {
	private Connection conn=null;
	private String sql=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private List<FoodType> list=null;
	
	@Override//添加菜系
	public void addFoodType(String typeName) {
		conn=getConn();
		sql="insert into foodType (typeName) values(?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, typeName);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}
	@Override//删除菜系
	public void deleteFoodType(int id) {
		conn=getConn();
		sql="delete from foodType where id=?";
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
	@Override//查询所有
	public List<FoodType> findFoodTypes() {
		list=new ArrayList<FoodType>();
		conn=getConn();
		sql="select*from foodType";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				FoodType foodType=new FoodType();
				foodType.setId(rs.getInt("id"));
				foodType.setTypeName(rs.getString("typeName"));
				list.add(foodType);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		return null;
	}
	@Override//修改菜系
	public void updateType(int id,String typeName){
		conn=getConn();
		sql="update foodType set typeName=? where id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,typeName);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}
	/*@Test
	public void test(){
		
		System.out.println(findFoodTypes().size());
	}
*/
	@Override//条件查询
	public List<FoodType> findTypeByName(String typeName) {
		list=new ArrayList<FoodType>();
		conn=getConn();
		sql="select*from foodType where typeName =?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, typeName);
			rs=ps.executeQuery();
			while(rs.next()){
				FoodType foodType=new FoodType();
				foodType.setId(rs.getInt("id"));
				foodType.setTypeName(rs.getString("typeName"));
				list.add(foodType);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		return null;
	}
}
