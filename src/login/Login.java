package login;

import javax.swing.*;

import client.Client;
import form.form;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

	public static void login() {

		JFrame f = new JFrame();

		f.setTitle("ϵͳ��¼����");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ���ô��ڵĴ�С��λ��
		f.setSize(600, 400);
		f.setLocation(420, 500);

		Container con = f.getContentPane();// ����һ������

		con.setLayout(new GridLayout(4,0));
		// ����һ���µİ���
		JPanel pan1 = new JPanel();

		JLabel title = new JLabel("��һ��Сѧ����ѧ����ϵͳ");
		title.setFont(new Font("����", Font.BOLD, 25));
		pan1.add(title);

		JPanel pan2 = new JPanel();
		JLabel name = new JLabel("�û���:");
		name.setFont(new Font("����", Font.BOLD, 20));
		pan2.add(name);
		JTextField nameTest = new JTextField(15);
		pan2.add(nameTest);
		// ����һ���µİ���
		JPanel pan3 = new JPanel();
		JLabel pass = new JLabel("��  ��:");
		pass.setFont(new Font("����", Font.BOLD, 20));
		pan3.add(pass);
		JPasswordField password = new JPasswordField(15);
		password.setEchoChar('*');
		pan3.add(password);

		con.add(pan1);
		con.add(pan2);
		con.add(pan3);

		JPanel pan4 = new JPanel();
		
		JButton b_log = new JButton("��½");
		
		b_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ��ȡ�û��������룬����У��
				String Username = nameTest.getText();
				String Password = password.getText();
				if (Username.equals("admin") && Password.equals("123456")) {
					JOptionPane.showMessageDialog(null, "��½�ɹ�!");
					f.dispose();
					form fm = new form();
					fm.show();
					
				} else {
					JOptionPane.showMessageDialog(null, "�˺Ż��������!");
					nameTest.setText("");
					password.setText("");
				}
			}
		});
		JButton b_exit = new JButton("�˳�");
		pan4.add(b_log);
		pan4.add(b_exit);
		con.add(pan4);
		f.setVisible(true);

	}

}