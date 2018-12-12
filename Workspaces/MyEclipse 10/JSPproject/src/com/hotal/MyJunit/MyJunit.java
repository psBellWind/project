package com.hotal.MyJunit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import org.junit.Test;





public class MyJunit {
	private static Connection conn=null;
	public static Connection getConn(){
		
		String url="jdbc:mysql://localhost:3306/hotel";
		String userName="root";
		String password="123456";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=(Connection)DriverManager.getConnection(url,userName,password);
			
		} catch (ClassNotFoundException e) {
			  e.printStackTrace();
		}catch (SQLException e) {
			  e.printStackTrace();
		}
		return conn;
	}
	public static void clossAll(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null)
				ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void test() {
		Connection cc=MyJunit.getConn();
		try {
			if(!cc.isClosed()){
				System.out.println("dawdawd");
				Statement statement=cc.createStatement();
				String sql="select*from web1";
				ResultSet rs=statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getInt("id")+"");
					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
