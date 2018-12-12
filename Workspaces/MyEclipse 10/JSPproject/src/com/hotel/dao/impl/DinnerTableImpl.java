package com.hotel.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



import com.hotal.MyJunit.MyJunit;
import com.hotal.entity.DinnerTable;
import com.hotel.dao.DinnerTableDao;



public class DinnerTableImpl extends MyJunit implements DinnerTableDao {
	Date date=new Date();
	SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Connection conn=null;
	private String sql;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private List<DinnerTable> list=null;
	@Override//添加
	public void updateAdd(String tableName) {
		conn=getConn();
		sql="insert into dinnerTable(tableName) values(?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, tableName);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}

	@Override//查询
	public List<DinnerTable> findTable(int tableStatus) {
		list=new ArrayList<DinnerTable>();
		conn=getConn();
		sql="select*from dinnertable where tableStatus=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, tableStatus);
			rs=ps.executeQuery();
			while (rs.next()) {
				DinnerTable dinnerTable=new DinnerTable();
				dinnerTable.setId(rs.getInt("id"));
				dinnerTable.setTableName(rs.getString("tableName"));
				dinnerTable.setTableStatus(tableStatus);
				dinnerTable.setOrderDate(rs.getString("orderDate"));
				list.add(dinnerTable);
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

	@Override//修改
	public void updateTable(int id,int tableStatus) {
		conn=getConn();
		sql="update dinnertable set tableStatus=?,orderDate=? where id=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, tableStatus);
			if (tableStatus==1) {
				ps.setString(2, sf.format(new Date()));
			}else {
				ps.setString(2, "");
			}
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			clossAll(conn, ps, rs);
		}
	}

	@Override//删除
	public void deletTable(int id) {
		conn=getConn();
		sql="delete from dinnertable where id=?";
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

	@Override//查询所有餐桌
	public List<DinnerTable> findAllTable() {
		list=new ArrayList<DinnerTable>();
		conn=getConn();
		sql="select*from dinnertable";
		try {
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while (rs.next()) {
				DinnerTable dinnerTable=new DinnerTable();
				dinnerTable.setId(rs.getInt("id"));
				dinnerTable.setTableStatus(rs.getInt("tableStatus"));
				dinnerTable.setTableName(rs.getString("tableName"));
				dinnerTable.setOrderDate(rs.getString("orderDate"));
				
				list.add(dinnerTable);
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
	/*@Test
	public void test(){
		DinnerTableDao dinnerTableDao=new DinnerTableImpl();
		
		for (DinnerTable dinnerTable: dinnerTableDao.findTable(0)) {
			System.out.println(dinnerTable.getTableName());
		}
		
	}*/

	@Override//按名字搜索
	public List<DinnerTable> findByName(String tableName) {
		list=new ArrayList<DinnerTable>();
		conn=getConn();
		sql="select*from dinnertable where tableName=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1,tableName);
			rs=ps.executeQuery();
			while (rs.next()) {
				DinnerTable dinnerTable=new DinnerTable();
				dinnerTable.setId(rs.getInt("id"));
				dinnerTable.setTableStatus(rs.getInt("tableStatus"));
				dinnerTable.setTableName(rs.getString("tableName"));
				dinnerTable.setOrderDate(rs.getString("orderDate"));
				
				list.add(dinnerTable);
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
