package form;

import javax.security.auth.login.LoginContext;
import javax.swing.*;

import client.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class form extends JFrame implements ActionListener {

	private JLabel lblShowTime;// ��ʾ��ǰʱ��

	private JTextField txtInput;// ���뵹��ֵ

	private JButton btnStart;

	private JLabel conshow1;
	private JLabel conshow2;
	private JLabel lblShow;

	public form() {

		super("��һ��Сѧ����ѧ����ϵͳ");

		Container con = this.getContentPane();

		this.setLocation(500, 300);

		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		con.setLayout(new GridLayout(4, 1));

		con.add(new TimePanel());

		con.add(new CountDownPanel());
		con.add(new ButtonPanel());
		con.add(new ContentPanel());
		
		this.setVisible(true);
		btnStart.addActionListener(this);
		Thread t1 = new TimeThread();
		t1.start();
		//Daojishi(60);
	}

	public class TimePanel extends JPanel {

		public TimePanel() {

			this.setSize(50, 50);
			;

			this.setBorder(BorderFactory.createTitledBorder("ʱ��"));

			lblShowTime = new JLabel();

			lblShowTime.setFont(new Font("����", Font.BOLD, 24));

			add(lblShowTime);

		}

	}

	public class CountDownPanel extends JPanel {

		public CountDownPanel() {
			this.setFont(new Font("�����п�", Font.BOLD, 30));
			this.setBorder(BorderFactory
					.createTitledBorder("����ʱ���������Զ��������ҳ�棬������׼��"));

			// this.setLayout(new GridLayout(2, 1));

			JPanel p1 = new JPanel();

			lblShow = new JLabel();

			lblShow.setFont(new Font("�����п�", Font.BOLD, 30));
			p1.add(lblShow);

			add(p1);

		}

	}

	// �]���l��
	public class ContentPanel extends JPanel {

		public ContentPanel() {
			this.setBorder(BorderFactory.createTitledBorder("ע������"));
			this.setLayout(new GridLayout(2, 1));
			JPanel p1 = new JPanel();

			conshow1 = new JLabel();

			conshow1.setFont(new Font("�����п�", Font.BOLD, 50));
			conshow1.setText("          1.����ʱ������Լ��趨");
			p1.add(conshow1);

			JPanel p2 = new JPanel();

			conshow2 = new JLabel();

			conshow2.setFont(new Font("�����п�", Font.BOLD, 50));
			conshow2.setText("2.���Ծ�����100��");

			p2.add(conshow2);

			add(p1);
			add(p2);

		}

	}

	public class ButtonPanel extends JPanel {

		public ButtonPanel() {

			this.setBorder(BorderFactory.createTitledBorder("���Ե����ťֱ�ӽ�����⣡ "));
			JPanel p1 = new JPanel();

			btnStart = new JButton("��ʼ����");

			// setVisible(true);
			btnStart.setPreferredSize(new Dimension(150, 60));
			btnStart.setFont(new Font("�����п�", Font.BOLD, 25));
			p1.add(btnStart);
			add(p1);

		}

	}

	public void Daojishi(int a) {

		boolean jishi = true;
		while (jishi == true) {

			a = --a;
			lblShow.setText("������   " + a + "   ��׼��ʱ��");
			if (a <= 10) {

				lblShow.setForeground(Color.red);
			}

			if (a == 0) {

				lblShow.setText("0");

				jishi = false;

				// JOptionPane.showMessageDialog(null, "ʱ�䵽��");
				this.dispose();
				new Client();
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

	// ������ʾʱ���߳�

	class TimeThread extends Thread {

		public void run() {

			while (true) {

				String str = "";// 9:33:12

				Calendar c = Calendar.getInstance();

				int h = c.get(Calendar.HOUR_OF_DAY);

				int m = c.get(Calendar.MINUTE);

				int s = c.get(Calendar.SECOND);

				if (m < 10 && s >= 10) {

					str = h + ":" + "0" + m + ":" + s;

					lblShowTime.setText("����ʱ��:" + str);

				} else if (m < 10 && s < 10) {

					str = h + ":" + "0" + m + ":" + "0" + s;

					lblShowTime.setText("����ʱ��:" + str);

				} else if (m >= 10 && s < 10) {

					str = h + ":" + m + ":" + "0" + s;

					lblShowTime.setText("����ʱ��:" + str);

				} else if (m >= 10 && s >= 10) {

					str = h + ":" + m + ":" + s;

					lblShowTime.setText("����ʱ��:" + str);

				}

				try {

					Thread.sleep(1000);

				} catch (InterruptedException e) {

					// TODO Auto-generated catch block

					e.printStackTrace();

				}

			}

		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStart) {
			this.dispose();
			new Client();

		}

	}
}
