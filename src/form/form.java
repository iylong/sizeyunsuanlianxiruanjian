package form;

import javax.security.auth.login.LoginContext;
import javax.swing.*;

import client.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class form extends JFrame implements ActionListener {

	private JLabel lblShowTime;// 显示当前时间

	private JTextField txtInput;// 输入倒计值

	private JButton btnStart;

	private JLabel conshow1;
	private JLabel conshow2;
	private JLabel lblShow;

	public form() {

		super("第一届小学生数学竞赛系统");

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

			this.setBorder(BorderFactory.createTitledBorder("时间"));

			lblShowTime = new JLabel();

			lblShowTime.setFont(new Font("楷体", Font.BOLD, 24));

			add(lblShowTime);

		}

	}

	public class CountDownPanel extends JPanel {

		public CountDownPanel() {
			this.setFont(new Font("华文行楷", Font.BOLD, 30));
			this.setBorder(BorderFactory
					.createTitledBorder("倒计时结束可以自动进入答题页面，请做好准备"));

			// this.setLayout(new GridLayout(2, 1));

			JPanel p1 = new JPanel();

			lblShow = new JLabel();

			lblShow.setFont(new Font("华文行楷", Font.BOLD, 30));
			p1.add(lblShow);

			add(p1);

		}

	}

	// 註明條件
	public class ContentPanel extends JPanel {

		public ContentPanel() {
			this.setBorder(BorderFactory.createTitledBorder("注意事项"));
			this.setLayout(new GridLayout(2, 1));
			JPanel p1 = new JPanel();

			conshow1 = new JLabel();

			conshow1.setFont(new Font("华文行楷", Font.BOLD, 50));
			conshow1.setText("          1.答题时间可以自己设定");
			p1.add(conshow1);

			JPanel p2 = new JPanel();

			conshow2 = new JLabel();

			conshow2.setFont(new Font("华文行楷", Font.BOLD, 50));
			conshow2.setText("2.本试卷满分100分");

			p2.add(conshow2);

			add(p1);
			add(p2);

		}

	}

	public class ButtonPanel extends JPanel {

		public ButtonPanel() {

			this.setBorder(BorderFactory.createTitledBorder("可以点击按钮直接进入答题！ "));
			JPanel p1 = new JPanel();

			btnStart = new JButton("开始答题");

			// setVisible(true);
			btnStart.setPreferredSize(new Dimension(150, 60));
			btnStart.setFont(new Font("华文行楷", Font.BOLD, 25));
			p1.add(btnStart);
			add(p1);

		}

	}

	public void Daojishi(int a) {

		boolean jishi = true;
		while (jishi == true) {

			a = --a;
			lblShow.setText("您还有   " + a + "   秒准备时间");
			if (a <= 10) {

				lblShow.setForeground(Color.red);
			}

			if (a == 0) {

				lblShow.setText("0");

				jishi = false;

				// JOptionPane.showMessageDialog(null, "时间到！");
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

	// 创建显示时间线程

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

					lblShowTime.setText("现在时间:" + str);

				} else if (m < 10 && s < 10) {

					str = h + ":" + "0" + m + ":" + "0" + s;

					lblShowTime.setText("现在时间:" + str);

				} else if (m >= 10 && s < 10) {

					str = h + ":" + m + ":" + "0" + s;

					lblShowTime.setText("现在时间:" + str);

				} else if (m >= 10 && s >= 10) {

					str = h + ":" + m + ":" + s;

					lblShowTime.setText("现在时间:" + str);

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
