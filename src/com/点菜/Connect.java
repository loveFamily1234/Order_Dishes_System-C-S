package com.���;

import java.sql.*;
public class Connect {
	 String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL���ݿ�����
     String connectDB = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Order";// ����Դ
    static Connection con=null;
//    static Statement stmt =null;
//    static ResultSet rs=null;
	public void connect(String user,String password){
	 
      try {
          Class.forName(JDriver);// �������ݿ����棬���ظ����ַ���������
      } catch (ClassNotFoundException e) {
          // e.printStackTrace();
          System.out.println("�������ݿ�����ʧ��");
          System.exit(0);
      }

      System.out.println("���ݿ������ɹ�");
      try {
    	  con=DriverManager.getConnection(connectDB, user, password);// �������ݿ����
          System.out.println("�������ݿ�ɹ�");
         
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("���ݿ����Ӵ���");
          System.exit(0);
      }
	}
}

