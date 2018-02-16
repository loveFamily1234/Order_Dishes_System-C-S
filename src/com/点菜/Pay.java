package com.点菜;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Pay extends JFrame implements ActionListener{
	JPanel jp0=new JPanel();
	JPanel jp1=new JPanel();
	JLabel jl[]={new JLabel("应收款(元):"),new JLabel("实付款(元):")};
	JTextField jtf[]=new JTextField[]{new JTextField(String.valueOf(Indent.price)),new JTextField("                ")};//必须有花括号中的内容
	JButton jb[]={new JButton("确定"),new JButton("返回")};
	FlowLayout fl=new FlowLayout();
	static float price2=0;//实付款
	public Pay(){
		jp0.setLayout(fl);
		jp1.setLayout(fl);
		for(int i=0;i<2;i++){
//			jl[i].setBounds(30,20+40*i,100,20);
//			jtf[i].setBounds(220,20+40*i,100,20);
//			jb[i].setBounds(30+90*i,120,80,20);
			jp0.add(jl[i]);
			jp0.add(jtf[i]);
			jp1.add(jb[i]);
			jb[i].addActionListener(this);
		}
		jtf[1].addActionListener(this);
		this.add(jp0,BorderLayout.NORTH);
		this.add(jp1,BorderLayout.CENTER);
		this.setTitle("付款");
//		this.pack();
		this.setBounds(550,200,300,120);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtf[1].requestFocus();//获取输入焦点
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jtf[1]){//实付款文本框
			price2=Float.parseFloat(jtf[1].getText());
			
		}else if(e.getSource()==jb[0]){//确定
			price2=Float.parseFloat(jtf[1].getText());
			this.dispose();
			new Change();
			
		}else if(e.getSource()==jb[1]){//返回

			try {
				String sql="DELETE FROM 应收账款";
				PreparedStatement ps=Connect.con.prepareStatement(sql);
				ps.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			this.dispose();
			new Function();
		}
	}
}
