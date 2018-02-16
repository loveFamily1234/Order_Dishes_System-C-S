package com.点菜;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Function extends JFrame implements ActionListener{
	 JPanel jp=new JPanel();
	 JButton[] jb={new JButton("点菜"),new JButton("订单"),new JButton("退出")};
     JLabel jl=new JLabel("欢迎光临");
	
	public Function(){
		jp.setLayout(new FlowLayout());
		jl.setHorizontalAlignment(JLabel.CENTER);
		for(int i=0;i<jb.length;i++){
			jp.add(jb[i]);
			jb[i].addActionListener(this);
		}
		this.add(jl,BorderLayout.NORTH);
		this.add(jp, BorderLayout.CENTER);
		
		this.setTitle("WELCOME");
		this.setVisible(true);
//		this.pack();
		this.setBounds(500,300,400,110);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb[0]){//点“点菜”按钮
			this.dispose();
			new Order();
		}else if(e.getSource()==jb[1]){//点“订单”按钮
			this.dispose();
			new Indent();
		}else if(e.getSource()==jb[2]){//点“退出”按钮
			 // 关闭连接
	         try {
//				Connect. rs.close();
//				Connect.stmt.close();// 关闭命令对象连接
				Connect.con.close();// 关闭数据库连接
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
}
