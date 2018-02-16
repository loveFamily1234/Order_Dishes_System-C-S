package com.点菜;

import java.sql.*;
public class Connect {
	 String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
     String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Order";// 数据源
    static Connection con=null;
//    static Statement stmt =null;
//    static ResultSet rs=null;
	public void connect(String user,String password){
	 
      try {
          Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
      } catch (ClassNotFoundException e) {
          // e.printStackTrace();
          System.out.println("加载数据库引擎失败");
          System.exit(0);
      }

      System.out.println("数据库驱动成功");
      try {
    	  con=DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
          System.out.println("连接数据库成功");
         
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("数据库连接错误");
          System.exit(0);
      }
	}
}

