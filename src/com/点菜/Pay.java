package com.���;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Pay extends JFrame implements ActionListener{
	JPanel jp0=new JPanel();
	JPanel jp1=new JPanel();
	JLabel jl[]={new JLabel("Ӧ�տ�(Ԫ):"),new JLabel("ʵ����(Ԫ):")};
	JTextField jtf[]=new JTextField[]{new JTextField(String.valueOf(Indent.price)),new JTextField("                ")};//�����л������е�����
	JButton jb[]={new JButton("ȷ��"),new JButton("����")};
	FlowLayout fl=new FlowLayout();
	static float price2=0;//ʵ����
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
		this.setTitle("����");
//		this.pack();
		this.setBounds(550,200,300,120);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtf[1].requestFocus();//��ȡ���뽹��
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jtf[1]){//ʵ�����ı���
			price2=Float.parseFloat(jtf[1].getText());
			
		}else if(e.getSource()==jb[0]){//ȷ��
			price2=Float.parseFloat(jtf[1].getText());
			this.dispose();
			new Change();
			
		}else if(e.getSource()==jb[1]){//����

			try {
				String sql="DELETE FROM Ӧ���˿�";
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
