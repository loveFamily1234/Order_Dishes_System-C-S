package com.点菜;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login  extends JFrame implements ActionListener{
	private JPanel jp=new JPanel();
	JLabel name=new JLabel("请输入用户名");
	JLabel password=new JLabel("请输入密码");
	private JLabel[] jl={name,password};
	JButton login=new JButton("登录");
	JButton reset=new JButton("重置");
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
		jPassword.setEchoChar('*');//设置密码框中的回显字符
        jPassword.addActionListener(this);
		this.add(jp);
		
		this.setTitle("登录数据库");
		this.setBounds(550,200,270,250);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==jName){//如果事件源为文本框
			jPassword.requestFocus();//切换输入焦点到密码框
		}else if(e.getSource()==jb[1]){//如果事件源为重置按钮
			//清空姓名文本框、密码框中的所有信息
			jName.setText("");
			jPassword.setText("");
			jName.requestFocus();//让输入焦点回到文本框
		}else{//如果事件源为登录按钮，则判断登录名和密码是否正确
			//判断用户名和密码是否匹配
			UserInformation u=new UserInformation();
			if(jName.getText().equals(u.user1)&&String.valueOf(jPassword.getPassword()).equals(u.password1)||
					jName.getText().equals(u.user2)&&String.valueOf(jPassword.getPassword()).equals(u.password2)){

				Connect con=new Connect();
				con.connect(jName.getText(), String.valueOf(jPassword.getPassword()));
//				String us=jName.getText();
//				System.out.println(us);
//				try {
//					PreparedStatement ps=Connect.con.prepareStatement("INSERT INTO 登录用户名 VALUES(?)");
//					ps.setString(1, us);
//					ps.executeUpdate();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				System.out.println("登录成功，欢迎您的到来！");
//				this.setVisible(false);
				this.dispose();
				new Function();
				
			}else{
				JOptionPane.showMessageDialog(this,"对不起，您的用户名或密码错误！","错误提示框",JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
}
