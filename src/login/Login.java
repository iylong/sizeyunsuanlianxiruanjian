package login;

import javax.swing.*;

import form.form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

	public static void login() {

		JFrame f = new JFrame();

		f.setTitle("系统登录界面");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置窗口的大小和位置
		f.setSize(400, 300);
		f.setLocation(420, 500);

		Container con = f.getContentPane();// 生成一个容器

		con.setLayout(new GridLayout(4,0));
		// 生成一个新的版面
		JPanel pan1 = new JPanel();

		JLabel title = new JLabel("第一届小学生数学竞赛系统");
		title.setFont(new Font("宋体", Font.BOLD, 25));
		title.setBounds(250, 300, 50, 50);
		pan1.add(title);
		//
		JPanel pan2 = new JPanel();
		JLabel name = new JLabel("用户名:");
		name.setFont(new Font("宋体", Font.BOLD, 20));
		pan2.add(name);
		JTextField nameTest = new JTextField(15);
		pan2.add(nameTest);
		// 生成一个新的版面
		JPanel pan3 = new JPanel();
		JLabel pass = new JLabel("密  码:");
		pass.setFont(new Font("宋体", Font.BOLD, 20));
		pan3.add(pass);
		JPasswordField password = new JPasswordField(15);
		password.setEchoChar('*');
		pan3.add(password);

		con.add(pan1);
		con.add(pan2);
		con.add(pan3);

		JPanel pan4 = new JPanel();
		JButton b_log = new JButton("登陆");
		b_log.setBounds(430, 500, 90, 40);
		b_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 获取用户名和密码，进行校验
				String Username = nameTest.getText();
				String Password = password.getText();
				if (Username.equals("admin") && Password.equals("123456")) {
					JOptionPane.showMessageDialog(null, "登陆成功!");
					f.dispose();
					form fm = new form();
					fm.show();
				} else {
					JOptionPane.showMessageDialog(null, "账号或密码错误!");
					nameTest.setText("");
					password.setText("");
				}
			}
		});
		JButton b_exit = new JButton("退出");
		pan4.add(b_log);
		pan4.add(b_exit);
		con.add(pan4);
		f.setVisible(true);

	}

}