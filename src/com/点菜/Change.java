package com.���;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Change extends JFrame implements ActionListener{
	JPanel jp0=new JPanel();
	JPanel jp1=new JPanel();
	JLabel jl=new JLabel("����(Ԫ):");
	JTextField jtf=new JTextField(String.valueOf(Pay.price2-Indent.price));
	JButton jb=new JButton("ȷ��");
	public Change(){
		jp0.add(jl);
		jp0.add(jtf);
		jp1.add(jb);
		jb.addActionListener(this);
		this.add(jp0,BorderLayout.NORTH);
		this.add(jp1,BorderLayout.CENTER);
		
		this.setTitle("����");
		this.setBounds(500,200,250,120);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb){//ȷ��
			String ddh=null;
			String xxh=null;
			String sql1=null;
			String sql2=null;
			String sql3=null;
			PreparedStatement ps1,ps2,ps3,ps4;
			ResultSet rs;
			try {
				sql1="SELECT ������,ϸ��� FROM Ӧ���˿�";
				sql2="DELETE FROM ����ϸ�� WHERE ������=? AND ϸ���=?";
				sql3="UPDATE ���� SET ��������=��������-1 WHERE ������=?";
				ps1=Connect.con.prepareStatement(sql1);
				rs=ps1.executeQuery();
				ps2=Connect.con.prepareStatement(sql2);
				ps3=Connect.con.prepareStatement(sql3);
				ps4=Connect.con.prepareStatement("DELETE FROM Ӧ���˿�");
				ps4.executeUpdate();
				while(rs.next()){
					ps2.setString(1, rs.getString(1));
					ps2.setString(2, rs.getString(2));
					ps2.executeUpdate();
					ps3.setString(1, rs.getString(1));
					ps3.executeUpdate();	
				}
				 sql1="DELETE FROM ���� WHERE ��������='0'";//������������Ϊ0ʱ��ɾ���ö���
	             ps1=Connect.con.prepareStatement(sql1);
	             ps1.executeUpdate();
				
	             //�ر�����
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
