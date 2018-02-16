package com.点菜;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Change extends JFrame implements ActionListener{
	JPanel jp0=new JPanel();
	JPanel jp1=new JPanel();
	JLabel jl=new JLabel("找零(元):");
	JTextField jtf=new JTextField(String.valueOf(Pay.price2-Indent.price));
	JButton jb=new JButton("确定");
	public Change(){
		jp0.add(jl);
		jp0.add(jtf);
		jp1.add(jb);
		jb.addActionListener(this);
		this.add(jp0,BorderLayout.NORTH);
		this.add(jp1,BorderLayout.CENTER);
		
		this.setTitle("找零");
		this.setBounds(500,200,250,120);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb){//确定
			String ddh=null;
			String xxh=null;
			String sql1=null;
			String sql2=null;
			String sql3=null;
			PreparedStatement ps1,ps2,ps3,ps4;
			ResultSet rs;
			try {
				sql1="SELECT 订单号,细则号 FROM 应收账款";
				sql2="DELETE FROM 订单细则 WHERE 订单号=? AND 细则号=?";
				sql3="UPDATE 订单 SET 订菜项数=订菜项数-1 WHERE 订单号=?";
				ps1=Connect.con.prepareStatement(sql1);
				rs=ps1.executeQuery();
				ps2=Connect.con.prepareStatement(sql2);
				ps3=Connect.con.prepareStatement(sql3);
				ps4=Connect.con.prepareStatement("DELETE FROM 应收账款");
				ps4.executeUpdate();
				while(rs.next()){
					ps2.setString(1, rs.getString(1));
					ps2.setString(2, rs.getString(2));
					ps2.executeUpdate();
					ps3.setString(1, rs.getString(1));
					ps3.executeUpdate();	
				}
				 sql1="DELETE FROM 订单 WHERE 订菜项数='0'";//当订菜项数减为0时，删除该订单
	             ps1=Connect.con.prepareStatement(sql1);
	             ps1.executeUpdate();
				
	             //关闭连接
	             rs.close();
	             ps1.close();
	             ps2.close();
	             ps3.close();
	             ps4.close();
	             
	             
	             this.dispose();
	             new Function();
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
