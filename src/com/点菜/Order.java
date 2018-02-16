package com.点菜;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Order extends JFrame implements ActionListener{
	 static int ddh=1;//订单号
	JPanel jp0=new JPanel();
	JPanel jp1=new JPanel();
	JCheckBox jcb1=new JCheckBox();
	JCheckBox jcb2=new JCheckBox();
	JCheckBox jcb3=new JCheckBox();
	JCheckBox jcb4=new JCheckBox();
	JCheckBox jcb5=new JCheckBox();
	JCheckBox jcb6=new JCheckBox();
	private JCheckBox jcb[]={jcb1,jcb2,jcb3,jcb4,jcb5,jcb6};
	JButton jb[]=new JButton[]{new JButton("提交订单"),new JButton("返回")};
	Statement stmt;
	ResultSet rs;
	Box vbox = Box.createVerticalBox();//创建纵向Box容器
//	SpinnerNumberModel snm[]={new SpinnerNumberModel(0,0,30,1),new SpinnerNumberModel(0,0,30,1),
//			new SpinnerNumberModel(0,0,30,1),new SpinnerNumberModel(0,0,30,1),
//			new SpinnerNumberModel(0,0,30,1),new SpinnerNumberModel(0,0,30,1)
//	};
	JTextField jtf[]={new JTextField("0"),new JTextField("0"),new JTextField("0"),new JTextField("0"),
			new JTextField("0"),new JTextField("0")
	};
	
	public Order(){
//		jp0.setLayout(new FlowLayout());
//		jp1.setLayout(new FlowLayout());
		try {
			
			stmt=Connect.con.createStatement();
			String sql="SELECT 订单号 FROM 订单 ORDER BY 订单号 DESC";
			rs=stmt.executeQuery(sql);
			//此处耗费了大量时间纠错
//			while(rs.next()){
			if(!rs.next()){
				ddh=1;
//				System.out.println("666666666");
			}else{
					String a=rs.getString(1);//此时a为“某数                        ”
//					System.out.println(a+"3");
					char c[]=a.toCharArray();
//					System.out.print(c[0]);
					int b=c[0]-'0';//将字符型转为整数型
//					int b=Integer.valueOf(a);
					ddh=b+1;
			}

		    rs = stmt.executeQuery("SELECT * FROM 菜品");
			for(int i=0;rs.next();i++){
				jcb[i].setText(rs.getString(2)+rs.getString(3)+"元");
//				jcb[i].setBounds(10,20+30*i, 100,30);
				vbox.add(Box.createVerticalStrut(20));
				vbox.add(jcb[i]);
				vbox.add(jtf[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jp0.add(vbox);
		for(int i=0;i<jb.length;i++){
			jb[i].addActionListener(this);
			jp1.add(jb[i]);
		}
		for(int i=0;i<jtf.length;i++){
			jcb[i].addActionListener(this);
//			jtf[i].addActionListener(this);
		}
		this.add(jp0,BorderLayout.NORTH);
		this.add(jp1, BorderLayout.CENTER);
		
		this.setTitle("点菜");
		this.setBounds(500,100,300,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
	   
	    int xzh=0;//细则号
	    int dcxs=0;//订菜项数
		if(e.getSource()==jcb[0]){//复选框
			if(jcb[0].isSelected()){
				jtf[0].setText("1");
			}else{
				jtf[0].setText("0");
			}
		}else  if(e.getSource()==jcb[1]){//复选框
			if(jcb[1].isSelected()){
				jtf[1].setText("1");
			}else{
				jtf[1].setText("0");
			}
	    }else  if(e.getSource()==jcb[2]){//复选框
	    	if(jcb[2].isSelected()){
				jtf[2].setText("1");
			}else{
				jtf[2].setText("0");
			}
        }else  if(e.getSource()==jcb[3]){//复选框
        	if(jcb[3].isSelected()){
				jtf[3].setText("1");
			}else{
				jtf[3].setText("0");
			}
        }else  if(e.getSource()==jcb[4]){//复选框
        	if(jcb[4].isSelected()){
				jtf[4].setText("1");
			}else{
				jtf[4].setText("0");
			}
        }else  if(e.getSource()==jcb[5]){//复选框
        	if(jcb[5].isSelected()){
				jtf[5].setText("1");
			}else{
				jtf[5].setText("0");
			}
        }else if(e.getSource()==jb[0]){//提交订单
//        	ddh++;
    	    for(int i=0;i<jcb.length;i++){
    	    	if(jcb[i].isSelected()){
        	    	dcxs++;
    	    	}
    	    }
        	for(int i=0;i<jcb.length;i++){
        		if(Integer.parseInt(jtf[i].getText())>0){
        			
        			try {
//        				String ddhs=((Integer)ddh).toString();
//        				String xzhs=((Integer)xzh).toString();
        				PreparedStatement ps=Connect.con.prepareStatement("SELECT 顾客号 FROM 顾客 WHERE 顾客名=?");//处理SQL命令，获取预编译语句对象
        				String u=Login.jName.getText();
        				ps.setString(1, u);//准备参数，插入数据
        				rs=ps.executeQuery();//执行SQL语句
//        				System.out.println(u);
//        				rs=stmt.executeQuery("SELECT 顾客号 FROM 顾客 WHERE 顾客名=u");
        				String gkh=null;//顾客号
        				while(rs.next()){
        				    gkh=rs.getString(1);//顾客号
        				}
//        				System.out.println(gkh);
//						stmt.executeUpdate("INSERT INTO 订单 VALUES(ddh,gkh,dcxs)");
        				ps=Connect.con.prepareStatement("INSERT INTO 订单 VALUES(?,?,?)");//处理SQL命令，获取预编译语句对象
        				ps.setInt(1, ddh);//准备参数，插入数据
        				ps.setString(2, gkh);
        				ps.setInt(3, dcxs);
        				ps.executeUpdate();//执行SQL语句
        				break;
        			}catch (SQLException e1) {
						e1.printStackTrace();
					}
        		}
        	}
        	for(int i=0;i<jcb.length;i++){
        		if(Integer.parseInt(jtf[i].getText())>0){
        			xzh++;
        			try{
//						String cph=((Integer)(i+1)).toString();//菜品号
//						 rs = stmt.executeQuery("SELECT 单价 FROM 菜品 WHERE 菜品号=i+1");
        				PreparedStatement ps=Connect.con.prepareStatement("SELECT 单价 FROM 菜品 WHERE 菜品号=?");//处理SQL命令，获取预编译语句对象
        				int cph=i+1;//菜品号
        				ps.setInt(1, cph);//准备参数，插入数据
        				rs=ps.executeQuery();//执行SQL语句
        				float dj=0;
        				while(rs.next()){
        					dj=rs.getFloat(1);//单价
        				}
						 int dhs=Integer.parseInt(jtf[i].getText());//订货数
						 float jg=dj*dhs;//价格
//						stmt.executeUpdate("INSERT INTO 订单细则 VALUES(ddh,xzh,i+1,rs.getFloat(1),dhs,Integer.parseInt(rs.getString(1))*dhs)");
						 ps=Connect.con.prepareStatement("INSERT INTO 订单细则 VALUES(?,?,?,?,?,?)");//处理SQL命令，获取预编译语句对象
						 ps.setInt(1, ddh);//准备参数，插入数据
						 ps.setInt(2, xzh);
						 ps.setInt(3, cph);
						 ps.setFloat(4, dj);
						 ps.setInt(5, dhs);
						 ps.setFloat(6, jg);
						 ps.executeUpdate();//执行SQL语句
						 //提交成功框
//						 JOptionPane.showMessageDialog(this,"提交成功","提示框",JOptionPane.INFORMATION_MESSAGE);	
						 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
        		}
//        		 JOptionPane.showMessageDialog(this,"提交成功","提示框",JOptionPane.INFORMATION_MESSAGE);	
        	}
        	if(jcb[0].isSelected()||jcb[1].isSelected()||jcb[2].isSelected()||jcb[3].isSelected()||jcb[4].isSelected()||
        			jcb[5].isSelected()){
        		JOptionPane.showMessageDialog(this,"提交成功","提示框",JOptionPane.INFORMATION_MESSAGE);	
        		ddh++;
        	}else{
        		JOptionPane.showMessageDialog(this,"您尚未点菜，请点菜","提示框",JOptionPane.INFORMATION_MESSAGE);
        	}
		}else if(e.getSource()==jb[1]){//返回
			this.dispose();
			new Function();
	    }
	}
}
