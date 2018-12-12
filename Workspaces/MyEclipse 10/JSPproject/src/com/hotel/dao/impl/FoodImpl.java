package com.hotel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hotal.MyJunit.MyJunit;
import com.hotal.entity.Food;
import com.hotel.dao.FoodDao;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.Closeable;

public class FoodImpl extends MyJunit implements FoodDao {
	private Connection conn=null;
	private String sql=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private List<Food> list=null;
	
	@Override//添加菜品
	public void addFood(Food food) {
		conn=getConn();
		sql="insert into food(foodName,foodType_id,price,mprice,remark,img) values(?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,food.getFoodName());
			ps.setInt(2,food.getFoodType_id());
			ps.setDouble(3,food.getPrice());
			ps.setDouble(4,food.getMprice());
			ps.setString(5,food.getRemark());
			ps.setString(6,food.getImg());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}

	@Override//查询
	public Food findFood(int id) {
		Food food=new Food();
		list=new ArrayList<Food>();
		conn=getConn();
		sql="select foodType_id,foodType.typeName,foodType.id AS 'typeId',food.id AS 'foodId',food.foodName,food.price,food.mprice,food.remark,food.img from food ,foodType where  food.id=? and foodType.id=foodType_id";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()){
				
				food.setId(rs.getInt("foodId"));
				food.setFoodTypeId(rs.getInt("typeId"));
				food.setFoodName(rs.getString("foodName"));
				food.setPrice(rs.getDouble("price"));
				food.setMprice(rs.getDouble("mprice"));
				food.setRemark(rs.getString("remark"));
				food.setImg(rs.getString("img"));
				food.setFoodType_id(rs.getInt("foodType_id"));
				food.setTypeName(rs.getString("typeName"));
				

			}
			return food;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		return null;
	}

	@Override//查询所有
	public List<Food> findAllFood() {
		list=new ArrayList<Food>();
		conn=getConn();
		sql="select foodType_id,foodType.typeName,foodType.id AS 'typeId',food.id AS 'foodId',food.foodName,food.price,food.mprice,food.remark,food.img from food ,foodType where  foodType.id=foodType_id";
		try {
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()){
				Food food=new Food();
				food.setId(rs.getInt("foodId"));
				food.setFoodTypeId(rs.getInt("typeId"));
				food.setFoodName(rs.getString("foodName"));
				food.setPrice(rs.getDouble("price"));
				food.setMprice(rs.getDouble("mprice"));
				food.setRemark(rs.getString("remark"));
				food.setImg(rs.getString("img"));
				food.setFoodType_id(rs.getInt("foodType_id"));
				food.setTypeName(rs.getString("typeName"));
				list.add(food);

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

	@Override//删除
	public void deleteFood(int id) {
		conn=getConn();
		sql="delete from food where id=?";
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

	@Override
	public void updateFood(Food food) {
		conn=getConn();
		sql="update food set foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? where id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,food.getFoodName());
			ps.setInt(2,food.getFoodType_id());
			ps.setDouble(3,food.getPrice());
			ps.setDouble(4,food.getMprice());
			ps.setString(5,food.getRemark());
			ps.setString(6,food.getImg());
			ps.setInt(7,food.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
		
	}

	@Override//通过菜系查询
	public List<Food> findFood(String typeName) {
		System.out.println("测试三,数据库"+typeName);
		list=new ArrayList<Food>();
		conn=getConn();
		sql="select foodType_id,foodType.typeName,foodType.id AS 'typeId',food.id AS 'foodId',food.foodName,food.price,food.mprice,food.remark,food.img from food ,foodType where  typeName=? and foodType.id=foodType_id";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, typeName);
			rs=ps.executeQuery();
			while(rs.next()){
				Food food=new Food();
				food.setId(rs.getInt("foodId"));
				food.setFoodTypeId(rs.getInt("typeId"));
				food.setFoodName(rs.getString("foodName"));
				food.setPrice(rs.getDouble("price"));
				food.setMprice(rs.getDouble("mprice"));
				food.setRemark(rs.getString("remark"));
				food.setImg(rs.getString("img"));
				food.setFoodType_id(rs.getInt("foodType_id"));
				food.setTypeName(rs.getString("typeName"));
				list.add(food);

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

	@Override
	public List<Food> selectFood(String foodName) {
		list=new ArrayList<Food>();
		conn=getConn();
		sql="select foodType_id,foodType.typeName,foodType.id AS 'typeId',food.id AS 'foodId',food.foodName,food.price,food.mprice,food.remark,food.img from food ,foodType where  foodName=? and foodType.id=foodType_id";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, foodName);
			rs=ps.executeQuery();
			while(rs.next()){
				Food food=new Food();
				food.setId(rs.getInt("foodId"));
				food.setFoodTypeId(rs.getInt("typeId"));
				food.setFoodName(rs.getString("foodName"));
				food.setPrice(rs.getDouble("price"));
				food.setMprice(rs.getDouble("mprice"));
				food.setRemark(rs.getString("remark"));
				food.setImg(rs.getString("img"));
				food.setFoodType_id(rs.getInt("foodType_id"));
				food.setTypeName(rs.getString("typeName"));
				list.add(food);

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
