package com.���;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login  extends JFrame implements ActionListener{
	private JPanel jp=new JPanel();
	JLabel name=new JLabel("�������û���");
	JLabel password=new JLabel("����������");
	private JLabel[] jl={name,password};
	JButton login=new JButton("��¼");
	JButton reset=new JButton("����");
	private JButton[] jb={login,reset};
    public static JTextField jName=new JTextField();
	private JPasswordField jPassword=new JPasswordField();
	public Login(){
		jp.setLayout(null);
		for(int i=0;i<2;i++){
			jl[i].setBounds(30,20+40*i,180,20);
			jb[i].setBounds(30+110*i,100,80,20);
			jp.add(jl[i]);
			jp.add(jb[i]);
			jb[i].addActionListener(this);
		}
		jName.setBounds(130,15,100,20);
		jp.add(jName);
		jName.addActionListener(this);
		jPassword.setBounds(130,60,100,20);
		jp.add(jPassword);
		jPassword.setEchoChar('*');//����������еĻ����ַ�
        jPassword.addActionListener(this);
		this.add(jp);
		
		this.setTitle("��¼���ݿ�");
		this.setBounds(550,200,270,250);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jName){//����¼�ԴΪ�ı���
			jPassword.requestFocus();//�л����뽹�㵽�����
		}else if(e.getSource()==jb[1]){//����¼�ԴΪ���ð�ť
			//��������ı���������е�������Ϣ
			jName.setText("");
			jPassword.setText("");
			jName.requestFocus();//�����뽹��ص��ı���
		}else{//����¼�ԴΪ��¼��ť�����жϵ�¼���������Ƿ���ȷ
			//�ж��û����������Ƿ�ƥ��
			UserInformation u=new UserInformation();
			if(jName.getText().equals(u.user1)&&String.valueOf(jPassword.getPassword()).equals(u.password1)||
					jName.getText().equals(u.user2)&&String.valueOf(jPassword.getPassword()).equals(u.password2)){

				Connect con=new Connect();
				con.connect(jName.getText(), String.valueOf(jPassword.getPassword()));
//				String us=jName.getText();
//				System.out.println(us);
//				try {
//					PreparedStatement ps=Connect.con.prepareStatement("INSERT INTO ��¼�û��� VALUES(?)");
//					ps.setString(1, us);
//					ps.executeUpdate();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				System.out.println("��¼�ɹ�����ӭ���ĵ�����");
//				this.setVisible(false);
				this.dispose();
				new Function();
				
			}else{
				JOptionPane.showMessageDialog(this,"�Բ��������û������������","������ʾ��",JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
}
