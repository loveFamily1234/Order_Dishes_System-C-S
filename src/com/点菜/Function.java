package com.���;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Function extends JFrame implements ActionListener{
	 JPanel jp=new JPanel();
	 JButton[] jb={new JButton("���"),new JButton("����"),new JButton("�˳�")};
     JLabel jl=new JLabel("��ӭ����");
	
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
		if(e.getSource()==jb[0]){//�㡰��ˡ���ť
			this.dispose();
			new Order();
		}else if(e.getSource()==jb[1]){//�㡰��������ť
			this.dispose();
			new Indent();
		}else if(e.getSource()==jb[2]){//�㡰�˳�����ť
			 // �ر�����
	         try {
//				Connect. rs.close();
//				Connect.stmt.close();// �ر������������
				Connect.con.close();// �ر����ݿ�����
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
}
