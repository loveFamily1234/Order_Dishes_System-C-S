package com.���;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Order extends JFrame implements ActionListener{
	 static int ddh=1;//������
	JPanel jp0=new JPanel();
	JPanel jp1=new JPanel();
	JCheckBox jcb1=new JCheckBox();
	JCheckBox jcb2=new JCheckBox();
	JCheckBox jcb3=new JCheckBox();
	JCheckBox jcb4=new JCheckBox();
	JCheckBox jcb5=new JCheckBox();
	JCheckBox jcb6=new JCheckBox();
	private JCheckBox jcb[]={jcb1,jcb2,jcb3,jcb4,jcb5,jcb6};
	JButton jb[]=new JButton[]{new JButton("�ύ����"),new JButton("����")};
	Statement stmt;
	ResultSet rs;
	Box vbox = Box.createVerticalBox();//��������Box����
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
			String sql="SELECT ������ FROM ���� ORDER BY ������ DESC";
			rs=stmt.executeQuery(sql);
			//�˴��ķ��˴���ʱ�����
//			while(rs.next()){
			if(!rs.next()){
				ddh=1;
//				System.out.println("666666666");
			}else{
					String a=rs.getString(1);//��ʱaΪ��ĳ��                        ��
//					System.out.println(a+"3");
					char c[]=a.toCharArray();
//					System.out.print(c[0]);
					int b=c[0]-'0';//���ַ���תΪ������
//					int b=Integer.valueOf(a);
					ddh=b+1;
			}

		    rs = stmt.executeQuery("SELECT * FROM ��Ʒ");
			for(int i=0;rs.next();i++){
				jcb[i].setText(rs.getString(2)+rs.getString(3)+"Ԫ");
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
		
		this.setTitle("���");
		this.setBounds(500,100,300,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
	   
	    int xzh=0;//ϸ���
	    int dcxs=0;//��������
		if(e.getSource()==jcb[0]){//��ѡ��
			if(jcb[0].isSelected()){
				jtf[0].setText("1");
			}else{
				jtf[0].setText("0");
			}
		}else  if(e.getSource()==jcb[1]){//��ѡ��
			if(jcb[1].isSelected()){
				jtf[1].setText("1");
			}else{
				jtf[1].setText("0");
			}
	    }else  if(e.getSource()==jcb[2]){//��ѡ��
	    	if(jcb[2].isSelected()){
				jtf[2].setText("1");
			}else{
				jtf[2].setText("0");
			}
        }else  if(e.getSource()==jcb[3]){//��ѡ��
        	if(jcb[3].isSelected()){
				jtf[3].setText("1");
			}else{
				jtf[3].setText("0");
			}
        }else  if(e.getSource()==jcb[4]){//��ѡ��
        	if(jcb[4].isSelected()){
				jtf[4].setText("1");
			}else{
				jtf[4].setText("0");
			}
        }else  if(e.getSource()==jcb[5]){//��ѡ��
        	if(jcb[5].isSelected()){
				jtf[5].setText("1");
			}else{
				jtf[5].setText("0");
			}
        }else if(e.getSource()==jb[0]){//�ύ����
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
        				PreparedStatement ps=Connect.con.prepareStatement("SELECT �˿ͺ� FROM �˿� WHERE �˿���=?");//����SQL�����ȡԤ����������
        				String u=Login.jName.getText();
        				ps.setString(1, u);//׼����������������
        				rs=ps.executeQuery();//ִ��SQL���
//        				System.out.println(u);
//        				rs=stmt.executeQuery("SELECT �˿ͺ� FROM �˿� WHERE �˿���=u");
        				String gkh=null;//�˿ͺ�
        				while(rs.next()){
        				    gkh=rs.getString(1);//�˿ͺ�
        				}
//        				System.out.println(gkh);
//						stmt.executeUpdate("INSERT INTO ���� VALUES(ddh,gkh,dcxs)");
        				ps=Connect.con.prepareStatement("INSERT INTO ���� VALUES(?,?,?)");//����SQL�����ȡԤ����������
        				ps.setInt(1, ddh);//׼����������������
        				ps.setString(2, gkh);
        				ps.setInt(3, dcxs);
        				ps.executeUpdate();//ִ��SQL���
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
//						String cph=((Integer)(i+1)).toString();//��Ʒ��
//						 rs = stmt.executeQuery("SELECT ���� FROM ��Ʒ WHERE ��Ʒ��=i+1");
        				PreparedStatement ps=Connect.con.prepareStatement("SELECT ���� FROM ��Ʒ WHERE ��Ʒ��=?");//����SQL�����ȡԤ����������
        				int cph=i+1;//��Ʒ��
        				ps.setInt(1, cph);//׼����������������
        				rs=ps.executeQuery();//ִ��SQL���
        				float dj=0;
        				while(rs.next()){
        					dj=rs.getFloat(1);//����
        				}
						 int dhs=Integer.parseInt(jtf[i].getText());//������
						 float jg=dj*dhs;//�۸�
//						stmt.executeUpdate("INSERT INTO ����ϸ�� VALUES(ddh,xzh,i+1,rs.getFloat(1),dhs,Integer.parseInt(rs.getString(1))*dhs)");
						 ps=Connect.con.prepareStatement("INSERT INTO ����ϸ�� VALUES(?,?,?,?,?,?)");//����SQL�����ȡԤ����������
						 ps.setInt(1, ddh);//׼����������������
						 ps.setInt(2, xzh);
						 ps.setInt(3, cph);
						 ps.setFloat(4, dj);
						 ps.setInt(5, dhs);
						 ps.setFloat(6, jg);
						 ps.executeUpdate();//ִ��SQL���
						 //�ύ�ɹ���
//						 JOptionPane.showMessageDialog(this,"�ύ�ɹ�","��ʾ��",JOptionPane.INFORMATION_MESSAGE);	
						 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
        		}
//        		 JOptionPane.showMessageDialog(this,"�ύ�ɹ�","��ʾ��",JOptionPane.INFORMATION_MESSAGE);	
        	}
        	if(jcb[0].isSelected()||jcb[1].isSelected()||jcb[2].isSelected()||jcb[3].isSelected()||jcb[4].isSelected()||
        			jcb[5].isSelected()){
        		JOptionPane.showMessageDialog(this,"�ύ�ɹ�","��ʾ��",JOptionPane.INFORMATION_MESSAGE);	
        		ddh++;
        	}else{
        		JOptionPane.showMessageDialog(this,"����δ��ˣ�����","��ʾ��",JOptionPane.INFORMATION_MESSAGE);
        	}
		}else if(e.getSource()==jb[1]){//����
			this.dispose();
			new Function();
	    }
	}
}
