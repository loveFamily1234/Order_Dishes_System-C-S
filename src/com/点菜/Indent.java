package com.点菜;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
public class Indent extends JFrame implements ActionListener{
	//创建列标题
	String head[]={"订单号","顾客名","订菜项数","细则号","菜品名","单价","数量","价格"};
	JButton jb[]={new JButton("全部付款"),new JButton("付款"),new JButton("取消订单"),new JButton("返回")};
	DefaultTableModel tableModel;//创建表格模型
	static JTable table;//由表格模型创建table对象
	JScrollPane scrollPane;//将表格对象table封装进滚动窗格
	JPanel jp=new JPanel();
	TableListener tableListener=new TableListener();//创建自定义的监听器的对象tableListener
	static float price=0;//应收款
	
	public Indent(){
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = Connect.con.prepareStatement("SELECT 顾客号 FROM 顾客 WHERE 顾客名=?");
			//处理SQL命令，获取预编译语句对象
			String u=Login.jName.getText();
			ps.setString(1, u);//准备参数，插入数据
			rs=ps.executeQuery();//执行SQL语句
			String gkh=null;//顾客号
			while(rs.next()){
			    gkh=rs.getString(1);//顾客号
			}
			String sql="SELECT 订单.订单号,顾客.顾客名,订菜项数,细则号,菜品名,菜品.单价,订货数,价格 FROM 顾客,菜品,订单,订单细则 WHERE 订单.订单号=订单细则.订单号 AND 订单.顾客号=顾客.顾客号 AND 订单细则.菜品号=菜品.菜品号 AND 顾客.顾客号=?";
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
		
		
		this.setTitle("订单信息");
		this.setVisible(true);
		this.setBounds(300,50,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jb[0]){//全部付款
			String gkh=null;
			String sql1="";
			String sql2="";
			PreparedStatement ps1,ps2;
			try {
				ps1 = Connect.con.prepareStatement("SELECT 顾客号 FROM 顾客 WHERE 顾客名=?");//处理SQL命令，获取预编译语句对象
				String u=Login.jName.getText();
				ps1.setString(1, u);//准备参数，插入数据
				ResultSet rs=ps1.executeQuery();//执行SQL语句
				while(rs.next()){
				    gkh=rs.getString(1);//顾客号
				}
				sql1="SELECT 顾客号,订单.订单号,细则号,价格 FROM 订单,订单细则 WHERE 订单.订单号=订单细则.订单号 AND 顾客号=?";
				sql2="INSERT INTO 应收账款 VALUES(?,?,?,?)";
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
				ps1=Connect.con.prepareStatement("SELECT SUM(应收金额) FROM 应收账款");
				rs=ps1.executeQuery();
				while(rs.next()){
					price=rs.getFloat(1);
				}
				//关闭连接
				rs.close();
				ps1.close();
				ps2.close();
				
				this.dispose();
				new Pay();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			
			
		}else if(e.getSource()==jb[1]){//付款
			DefaultTableModel temp=(DefaultTableModel)table.getModel();//获得表格模型
			int r=temp.getRowCount();//获得表格的行数
			if(r>0){
				price=Float.parseFloat((String)table.getValueAt(tableListener.row, 7));
			}
			String gkh=null;
			String ddh=null;
			String xzh=null;
			ddh=(String)table.getValueAt(tableListener.row, 0);
			xzh=(String)table.getValueAt(tableListener.row, 3);
			try {
				PreparedStatement ps=Connect.con.prepareStatement("SELECT 顾客号 FROM 顾客 WHERE 顾客名=?");//处理SQL命令，获取预编译语句对象
				String u=Login.jName.getText();
				ps.setString(1, u);//准备参数，插入数据
				ResultSet rs=ps.executeQuery();//执行SQL语句
				while(rs.next()){
				    gkh=rs.getString(1);//顾客号
				}
				String sql="INSERT INTO 应收账款 VALUES(?,?,?,?)";
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
			
			
		}else if(e.getSource()==jb[2]){//取消订单
			DefaultTableModel temp=(DefaultTableModel)table.getModel();//获得表格模型
			int r=temp.getRowCount();//获得表格的行数
			String ddh=null;
//			String dcxs=null;
			String xzh=null;
			if(r>0){//检查行数是否大于0，如果大于0，删除指定行
				 ddh=(String)table.getValueAt(tableListener.row, 0);//获取删除订单的订单号
//				dcxs=(String)table.getValueAt(tableListener.row, 2);
				xzh=(String)table.getValueAt(tableListener.row, 3);//获取删除订单的订单细则号
				temp.removeRow(tableListener.row);
			}
//			System.out.println(ddh);
//			System.out.println(xzh);
			
			try {
				//删除数据库中的订单细则表中的相关数据
				String sql="DELETE FROM 订单细则 WHERE 订单号=? AND 细则号=?";
				PreparedStatement ps=Connect.con.prepareStatement(sql);
				ps.setString(1, ddh);
				ps.setString(2,xzh);
				ps.executeUpdate();
//				System.out.println("删除成功");
				//将订单表中的相关订菜项数减1
				sql="UPDATE 订单 SET 订菜项数=订菜项数-1 WHERE 订单号=?";
                ps=Connect.con.prepareStatement(sql);
                ps.setString(1, ddh);
                ps.executeUpdate();
//                System.out.println("更新成功");
                sql="DELETE FROM 订单 WHERE 订菜项数='0'";//当订菜项数减为0时，删除该订单
                ps=Connect.con.prepareStatement(sql);
                ps.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
			new Indent();
			
		}else if(e.getSource()==jb[3]){//返回
			this.dispose();
			new Function();
		}
	}
	
}


//自定义的监听器内部类TableListener，实现了2个监听器接口
class TableListener implements ListSelectionListener,TableColumnModelListener{
	int row=0;//定义行索引
	int col=0;//定义列索引
	//实现ListSelectionListener监听接口中的方法valueChanged
	public void valueChanged(ListSelectionEvent e){//当选中发生变化时会调用该方法
		row=Indent.table.getSelectedRow();//获取当前选择的行索引
	}
	//实现TableColumnModelListener监听接口中的方法columnSelectionChanged
	public void columnSelectionChanged(ListSelectionEvent e){//当选中列发生变化时
		
	}
	public void columnMarginChanged(ChangeEvent ce){}
	public void columnMoved(TableColumnModelEvent tcme){}
	public void columnRemoved(TableColumnModelEvent tcme){}
	public void columnAdded(TableColumnModelEvent tcme){}
}


















