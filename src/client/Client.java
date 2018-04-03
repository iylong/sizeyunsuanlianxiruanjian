package client;

import javax.swing.*;

import background.Background;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import jfewwchart.JFreeChartshow;

public class Client extends JFrame implements ActionListener {

	private JLabel labTime; // ��ʾʱ��ı�ǩ
	private JLabel labAccuracy; 
	private JButton btntongji;
	private JPanel jpMain, jpTest; // ��Ŀ���
	private Background background; // �����̨
	private String his_rightNum, his_allNum; // ��¼��ʷ��ȷ��������
	private Integer minutes = 0, seconds = 0;// ��¼ʱ��
	private String minStr, secStr; // ��Ӧ����ת�����ַ���
	private BufferedReader reader; // ��ȡ��ͬ���Զ�Ӧ����ʾ��Ϣ
	private JButton btnSubmit, btnReset; // �ύ�����ð�ť
	private ArrayList<String> tips = new ArrayList<String>(); // �����ȡ�Ľ����õ�����������
	private ArrayList<String> count_array = new ArrayList<String>(); //
	private String[] questions = new String[Background.testNum]; // ���ڴ�����Ŀ
	private JPanel[] jpQuestions = new JPanel[Background.testNum]; // ��ʾ��Ŀ�����
	private JLabel[] labQuestions = new JLabel[Background.testNum]; // ��ʾ��Ŀ�ı�ǩ
	private JTextField[] tfdAnswer = new JTextField[Background.testNum]; // ��д�𰸵��ı�
	private String[] answers = new String[Background.testNum]; // �û���д�Ĵ𰸼�
	private String[] wrong; // �������źϼ�
	private boolean isEnd = false; // ����Ƿ�����������ȷ���Ƿ�Ҫ������ʱ
	private static ArrayList<String> scores = new ArrayList<String>();

	// �ͻ��˹�����
	public Client() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// ���������̨�����ڳ���͸ľ�
		background = new Background();
		// ������Ŀ
		background.createTest();
		// ��ȡ��������Ŀ
		questions = background.getQuestions();
		// ��������
		this.settype();
		// �������
		createComponent();

		this.setTitle(tips.get(0));
		this.setSize(700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	// �������
	public void createComponent() {
		// jpMain�����Ϊ������������������
		jpMain = new JPanel();
		jpMain.setBackground(Color.gray);
		jpMain.setLayout(null);
		showTime(); // ���ʱ���ǩ
		// showHistory(); // �����ȷ�ʱ�ǩ
		// xianshi();
		// ����ύ�����ð�ť
		btnSubmit = new JButton(tips.get(3));
		btnSubmit.setBounds(220, 500, 90, 40);
		btnSubmit.addActionListener(this);
		jpMain.add(btnSubmit);
		btnReset = new JButton(tips.get(4));
		btnReset.setBounds(430, 500, 90, 40);
		btnReset.addActionListener(this);
		jpMain.add(btnReset);
		// �������
		jpTest = new JPanel();
		jpTest.setLayout(new GridLayout(Background.testNum, 1, 20, 20));
		jpTest.setBackground(Color.gray);
		for (int i = 0; i < Background.testNum; i++) {
			jpQuestions[i] = new JPanel();
			jpQuestions[i].setBackground(Color.gray);
			jpQuestions[i].setLayout(null); // ���ò���Ϊnull
			// ������Ŀ��ǩ
			labQuestions[i] = new JLabel(questions[i], JLabel.CENTER);
			labQuestions[i].setFont(new Font("Consolas", 0, 20));
			jpQuestions[i].add(labQuestions[i]);
			labQuestions[i].setBounds(50, 0, 350, 25);
			// ������д�𰸱�ǩ
			tfdAnswer[i] = new JTextField(8);
			tfdAnswer[i].setFont(new Font("Consolas", 0, 12));
			tfdAnswer[i].setBackground(Color.white);
			jpQuestions[i].add(tfdAnswer[i]);
			tfdAnswer[i].setBounds(350, 0, 60, 25);
			// ��ӱ�ǩ
			jpTest.add(jpQuestions[i]);
		}
		jpMain.add(jpTest).setBounds(100, 60, 500, 400);
		add(jpMain);
	}

	// ��ʾ����ʱ��
	public void showTime() {
		labTime = new JLabel(tips.get(1) + "00:00");
		// labTime = new JLabel("ʱ��:" + "00:00");
		labTime.setBounds(600, 0, 120, 50);
		jpMain.add(labTime);
		// ������ʱ�߳�
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						seconds++;
						if (seconds >= 60) {
							seconds = 0;
							minutes++;
						}
						// ������������������
						if (seconds < 10)
							secStr = "0" + seconds.toString();
						else
							secStr = seconds.toString();
						if (minutes < 10)
							minStr = "0" + minutes.toString();
						else
							minStr = minutes.toString();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					labTime.setText(tips.get(1) + minStr + ":" + secStr);
					if (isEnd)
						break;
				}
			}
		}.start();
	}

	// ��ʾ��ʷ��ȷ��
	// public void showHistory() {
	// labAccuracy = new JLabel();
	// labAccuracy.setBounds(50, 0, 120, 50);
	// try {
	// reader = new BufferedReader(new FileReader(new
	// File("history/accuracy.txt")));
	// his_rightNum = reader.readLine();
	// his_allNum = reader.readLine();
	// reader.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// labAccuracy.setText(tips.get(2) + his_rightNum + "/" + his_allNum);
	// jpMain.add(labAccuracy);
	// }
	// ��ʾͳ�ư�ť
	public void xianshi() {
		btntongji = new JButton();
		btntongji.setBounds(50, 0, 120, 50);
		btntongji.setText("����ͳ��");
		jpMain.add(btntongji);
	}

	// ���ÿͻ�������
	public void settype() {
		String[] choiceLanguage = { "����", "����", "������" };
		String choicetype = (String) JOptionPane.showInputDialog(null,
				"ѡ������:\n", "ѡ����Ŀ����", JOptionPane.PLAIN_MESSAGE, new ImageIcon(
						"icon.png"), choiceLanguage, "����");
		if (choicetype == "����" || choicetype == "����" || choicetype == "������") {

			try {
				reader = new BufferedReader(new FileReader(new File(
						"type/����.txt")));
				String s;
				while ((s = reader.readLine()) != null) {
					tips.add(s);
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// //��������
	// public void settype() {
	// String[] choicetype = { "����", "����", "������" };
	// String questype = (String) JOptionPane.showInputDialog(null, "ѡ������:\n",
	// "ѡ����Ŀ����",
	// JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), choicetype, "����");
	// if (questype == null) {
	// System.exit(-1);
	// } else if(questype==choicetype[1]){
	// System.out.println(questype);
	//
	// }
	// }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSubmit) {
			isEnd = !isEnd; // ��ʱ����
			for (int i = 0; i < Background.testNum; i++) {
				answers[i] = tfdAnswer[i].getText(); // ��ȡ�û��Ĵ�
			}
			wrong = background.checkAnswer(answers); // ��ô������
			String l = String.valueOf(Background.testNum - wrong.length);
			count_array.add(l);
			for (String e1 : count_array) {
				System.out.println(e1);
			}
			// �༭������
			String s = null;
			if (wrong.length == 0)
				s = tips.get(5);
			else {
				s = tips.get(6) + "\n";
				String standardAnswer[] = new String[Background.testNum];
				standardAnswer = background.getStandardAnswer();
				for (int i = 0; i < wrong.length; i++) {
					s = s + new Integer(wrong[i]) + ":"
							+ standardAnswer[new Integer(wrong[i]) - 1];
					s = s + "\n";
				}
			}
			JOptionPane.showMessageDialog(null, s, "report",
					JOptionPane.PLAIN_MESSAGE);

			int score = Background.testNum - wrong.length;
			try {

				Background.addscore(score * 10);
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		if (e.getSource() == btnReset) {
			// this.dispose();
			JFreeChartshow chart = new JFreeChartshow("�ɼ�ͳ��ͼ");
			chart.pack();// �Ժ��ʵĴ�С��ʾ
			chart.setVisible(true);

		}
	}

}
