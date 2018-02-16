package com.���;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
public class Indent extends JFrame implements ActionListener{
	//�����б���
	String head[]={"������","�˿���","��������","ϸ���","��Ʒ��","����","����","�۸�"};
	JButton jb[]={new JButton("ȫ������"),new JButton("����"),new JButton("ȡ������"),new JButton("����")};
	DefaultTableModel tableModel;//�������ģ��
	static JTable table;//�ɱ��ģ�ʹ���table����
	JScrollPane scrollPane;//��������table��װ����������
	JPanel jp=new JPanel();
	TableListener tableListener=new TableListener();//�����Զ���ļ������Ķ���tableListener
	static float price=0;//Ӧ�տ�
	
	public Indent(){
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = Connect.con.prepareStatement("SELECT �˿ͺ� FROM �˿� WHERE �˿���=?");
			//����SQL�����ȡԤ����������
			String u=Login.jName.getText();
			ps.setString(1, u);//׼����������������
			rs=ps.executeQuery();//ִ��SQL���
			String gkh=null;//�˿ͺ�
			while(rs.next()){
			    gkh=rs.getString(1);//�˿ͺ�
			}
			String sql="SELECT ����.������,�˿�.�˿���,��������,ϸ���,��Ʒ��,��Ʒ.����,������,�۸� FROM �˿�,��Ʒ,����,����ϸ�� WHERE ����.������=����ϸ��.������ AND ����.�˿ͺ�=�˿�.�˿ͺ� AND ����ϸ��.��Ʒ��=��Ʒ.��Ʒ�� AND �˿�.�˿ͺ�=?";
			ps = Connect.con.prepareStatement(sql);
			ps.setString(1, gkh);
			rs=ps.executeQuery();
			 String[][] data = new String[40][8];
			for(int i=0;rs.next();i++){
				for(int j=0;j<8;j++){
//					String data[i][]={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
				   
					data[i][j]=rs.getString(j+1);
				}
			}
//			 for(int i=0;rs.next();i++){
//				 data[i][] ={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
//			 }
			
			tableModel=new DefaultTableModel(data,head);
			table=new JTable(tableModel);
			scrollPane=new JScrollPane(table);
			table.createDefaultColumnsFromModel();
			table.getSelectionModel().addListSelectionListener(tableListener);
			table.getColumnModel().addColumnModelListener(tableListener);
			for(int i=0;i<jb.length;i++){
				jb[i].addActionListener(this);
				jp.add(jb[i]);
			}
			this.add(scrollPane,BorderLayout.NORTH);
			this.add(jp, BorderLayout.CENTER);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		this.setTitle("������Ϣ");
		this.setVisible(true);
		this.setBounds(300,50,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb[0]){//ȫ������
			String gkh=null;
			String sql1="";
			String sql2="";
			PreparedStatement ps1,ps2;
			try {
				ps1 = Connect.con.prepareStatement("SELECT �˿ͺ� FROM �˿� WHERE �˿���=?");//����SQL�����ȡԤ����������
				String u=Login.jName.getText();
				ps1.setString(1, u);//׼����������������
				ResultSet rs=ps1.executeQuery();//ִ��SQL���
				while(rs.next()){
				    gkh=rs.getString(1);//�˿ͺ�
				}
				sql1="SELECT �˿ͺ�,����.������,ϸ���,�۸� FROM ����,����ϸ�� WHERE ����.������=����ϸ��.������ AND �˿ͺ�=?";
				sql2="INSERT INTO Ӧ���˿� VALUES(?,?,?,?)";
				ps1=Connect.con.prepareStatement(sql1);
				ps1.setString(1, gkh);
				rs=ps1.executeQuery();
				ps2=Connect.con.prepareStatement(sql2);
				while(rs.next()){
					ps2.setString(1, rs.getString(1));
					ps2.setString(2, rs.getString(2));
					ps2.setString(3, rs.getString(3));
					ps2.setFloat(4, rs.getFloat(4));
					ps2.executeUpdate();
				}
				ps1=Connect.con.prepareStatement("SELECT SUM(Ӧ�ս��) FROM Ӧ���˿�");
				rs=ps1.executeQuery();
				while(rs.next()){
					price=rs.getFloat(1);
				}
				//�ر�����
				rs.close();
				ps1.close();
				ps2.close();
				
				this.dispose();
				new Pay();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			
			
		}else if(e.getSource()==jb[1]){//����
			DefaultTableModel temp=(DefaultTableModel)table.getModel();//��ñ��ģ��
			int r=temp.getRowCount();//��ñ�������
			if(r>0){
				price=Float.parseFloat((String)table.getValueAt(tableListener.row, 7));
			}
			String gkh=null;
			String ddh=null;
			String xzh=null;
			ddh=(String)table.getValueAt(tableListener.row, 0);
			xzh=(String)table.getValueAt(tableListener.row, 3);
			try {
				PreparedStatement ps=Connect.con.prepareStatement("SELECT �˿ͺ� FROM �˿� WHERE �˿���=?");//����SQL�����ȡԤ����������
				String u=Login.jName.getText();
				ps.setString(1, u);//׼����������������
				ResultSet rs=ps.executeQuery();//ִ��SQL���
				while(rs.next()){
				    gkh=rs.getString(1);//�˿ͺ�
				}
				String sql="INSERT INTO Ӧ���˿� VALUES(?,?,?,?)";
				ps=Connect.con.prepareStatement(sql);
				ps.setString(1, gkh);
				ps.setString(2,ddh);
				ps.setString(3, xzh);
				ps.setFloat(4, price);
				ps.executeUpdate();
               
				this.dispose();
				new Pay();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}else if(e.getSource()==jb[2]){//ȡ������
			DefaultTableModel temp=(DefaultTableModel)table.getModel();//��ñ��ģ��
			int r=temp.getRowCount();//��ñ�������
			String ddh=null;
//			String dcxs=null;
			String xzh=null;
			if(r>0){//��������Ƿ����0���������0��ɾ��ָ����
				 ddh=(String)table.getValueAt(tableListener.row, 0);//��ȡɾ�������Ķ�����
//				dcxs=(String)table.getValueAt(tableListener.row, 2);
				xzh=(String)table.getValueAt(tableListener.row, 3);//��ȡɾ�������Ķ���ϸ���
				temp.removeRow(tableListener.row);
			}
//			System.out.println(ddh);
//			System.out.println(xzh);
			
			try {
				//ɾ�����ݿ��еĶ���ϸ����е��������
				String sql="DELETE FROM ����ϸ�� WHERE ������=? AND ϸ���=?";
				PreparedStatement ps=Connect.con.prepareStatement(sql);
				ps.setString(1, ddh);
				ps.setString(2,xzh);
				ps.executeUpdate();
//				System.out.println("ɾ���ɹ�");
				//���������е���ض���������1
				sql="UPDATE ���� SET ��������=��������-1 WHERE ������=?";
                ps=Connect.con.prepareStatement(sql);
                ps.setString(1, ddh);
                ps.executeUpdate();
//                System.out.println("���³ɹ�");
                sql="DELETE FROM ���� WHERE ��������='0'";//������������Ϊ0ʱ��ɾ���ö���
                ps=Connect.con.prepareStatement(sql);
                ps.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
			new Indent();
			
		}else if(e.getSource()==jb[3]){//����
			this.dispose();
			new Function();
		}
	}
	
}


//�Զ���ļ������ڲ���TableListener��ʵ����2���������ӿ�
class TableListener implements ListSelectionListener,TableColumnModelListener{
	int row=0;//����������
	int col=0;//����������
	//ʵ��ListSelectionListener�����ӿ��еķ���valueChanged
	public void valueChanged(ListSelectionEvent e){//��ѡ�з����仯ʱ����ø÷���
		row=Indent.table.getSelectedRow();//��ȡ��ǰѡ���������
	}
	//ʵ��TableColumnModelListener�����ӿ��еķ���columnSelectionChanged
	public void columnSelectionChanged(ListSelectionEvent e){//��ѡ���з����仯ʱ
		
	}
	public void columnMarginChanged(ChangeEvent ce){}
	public void columnMoved(TableColumnModelEvent tcme){}
	public void columnRemoved(TableColumnModelEvent tcme){}
	public void columnAdded(TableColumnModelEvent tcme){}
}


















