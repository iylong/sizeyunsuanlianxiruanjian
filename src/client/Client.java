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

	private JLabel labTime; // 显示时间的标签
	private JLabel labAccuracy; // 显示正确率
	private JButton btntongji ;
	private JPanel jpMain, jpTest; // 题目面板
	private Background background; // 程序后台
	private String his_rightNum, his_allNum; // 记录历史正确错误数量
	private Integer minutes = 0, seconds = 0;// 记录时间
	private String minStr, secStr; // 对应分秒转化成字符串
	private BufferedReader reader; // 读取不同语言对应的提示信息
	private JButton btnSubmit, btnReset; // 提交、重置按钮
	private ArrayList<String> tips = new ArrayList<String>(); // 储存读取的界面用到的文字内容
	private ArrayList<String> types = new ArrayList<String>(); // 
	private String[] questions = new String[Background.testNum]; // 用于储存题目
	private JPanel[] jpQuestions = new JPanel[Background.testNum]; // 显示题目的面板
	private JLabel[] labQuestions = new JLabel[Background.testNum]; // 显示题目的标签
	private JTextField[] tfdAnswer = new JTextField[Background.testNum]; // 填写答案的文本
	private String[] answers = new String[Background.testNum]; // 用户填写的答案集
	private String []wrong; // 错误的题号合集
	private boolean isEnd=false; // 标记是否答题结束，来确定是否要继续计时
	
	// 客户端构造器
	public Client() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// 创建程序后台，用于出题和改卷
		background = new Background();
		// 创建题目
		background.createTest();
		// 获取完整的题目
		questions = background.getQuestions();
		// 设置语言
this.setLanguage();
		//this.settype();
		// 创建面板
		createComponent();

		this.setTitle(tips.get(0));
		this.setSize(700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//btntongji.addActionListener(this);
		this.setVisible(true);
	}

	// 创建面板
	public void createComponent() {
		// jpMain面板作为所有其他部件的载体
		jpMain = new JPanel();
		jpMain.setBackground(Color.gray);
		jpMain.setLayout(null);
		showTime(); // 添加时间标签
//showHistory(); // 添加正确率标签
//xianshi();
		// 添加提交、重置按钮
		btnSubmit = new JButton(tips.get(3));
		btnSubmit.setBounds(220, 500, 90, 40);
		btnSubmit.addActionListener(this);
		jpMain.add(btnSubmit);
		btnReset = new JButton(tips.get(4));
		btnReset.setBounds(430, 500, 90, 40);
		btnReset.addActionListener(this);
		jpMain.add(btnReset);
		// 答题面板
		jpTest = new JPanel();
		jpTest.setLayout(new GridLayout(Background.testNum, 1, 20, 20));
		jpTest.setBackground(Color.gray);
		for (int i = 0; i < Background.testNum; i++) {
			jpQuestions[i] = new JPanel();
			jpQuestions[i].setBackground(Color.gray);
			jpQuestions[i].setLayout(null); // 设置布局为null
			// 创建题目标签
			labQuestions[i] = new JLabel(questions[i], JLabel.CENTER);
			labQuestions[i].setFont(new Font("Consolas", 0, 20));
			jpQuestions[i].add(labQuestions[i]);
			labQuestions[i].setBounds(50, 0, 350, 25);
			// 创建填写答案标签
			tfdAnswer[i] = new JTextField(8);
			tfdAnswer[i].setFont(new Font("Consolas", 0, 12));
			tfdAnswer[i].setBackground(Color.white);
			jpQuestions[i].add(tfdAnswer[i]);
			tfdAnswer[i].setBounds(350, 0, 60, 25);
			// 添加标签
			jpTest.add(jpQuestions[i]);
		}
		jpMain.add(jpTest).setBounds(100, 60, 500, 400);
		add(jpMain);
	}

	// 显示答题时间
	public void showTime() {
labTime = new JLabel(tips.get(1) + "00:00");
//labTime = new JLabel("时间:" + "00:00");
		labTime.setBounds(600, 0, 120, 50);
		jpMain.add(labTime);
		// 启动记时线程
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
						// 修正分钟数和秒钟数
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
					if(isEnd)
						break;
				}
			}
		}.start();
	}

	// 显示历史正确率
//	public void showHistory() {
//		labAccuracy = new JLabel();
//		labAccuracy.setBounds(50, 0, 120, 50);
//		try {
//			reader = new BufferedReader(new FileReader(new File("history/accuracy.txt")));
//			his_rightNum = reader.readLine();
//			his_allNum = reader.readLine();
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		labAccuracy.setText(tips.get(2) + his_rightNum + "/" + his_allNum);
//		jpMain.add(labAccuracy);
//	}
  //显示统计按钮
	public void  xianshi(){
		btntongji=new JButton();
		btntongji.setBounds(50, 0, 120, 50);
		btntongji.setText("数据统计");
	jpMain.add(btntongji);
	}
	
	// 设置客户端语言
	public void setLanguage() {
		String[] choiceLanguage = { "整数", "分数", "带括号" };
		String choicetype = (String) JOptionPane.showInputDialog(null,"选择类型:\n", "选择题目类型",
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), choiceLanguage, "简体");
		if (choicetype == null) {
			System.exit(-1);
		} else {
			try {
				reader = new BufferedReader(new FileReader(new File("type/" + choicetype + ".txt")));
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
//设题类型
	public void settype() {
		String[] choicetype = { "整数", "分数", "带括号" };
		String questype = (String) JOptionPane.showInputDialog(null, "选择类型:\n", "选择题目类型",
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), choicetype, "整数");
		if (questype == null) {
			System.exit(-1);
		} else if(questype==choicetype[1]){
			System.out.println(questype);
			
			
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSubmit) {
			isEnd=!isEnd;  //计时结束
			for (int i = 0; i < Background.testNum; i++) {
				answers[i]=tfdAnswer[i].getText();  // 提取用户的答案
			}
			wrong= background.checkAnswer(answers);  // 获得错题题号
			
			// 编辑反馈表
			String s=null;
			if(wrong.length==0)
				s=tips.get(5);
			else{
				s=tips.get(6)+"\n";
				String standardAnswer[]=new String[Background.testNum];
				standardAnswer=background.getStandardAnswer();
				for(int i=0;i<wrong.length;i++){
					s=s+new Integer(wrong[i])+":"+standardAnswer[new Integer(wrong[i])-1];
					s=s+"\n";
				}
			}
			JOptionPane.showMessageDialog(null, s, "report",JOptionPane.PLAIN_MESSAGE);
			background.upDate(new Integer(his_rightNum+(Background.testNum-wrong.length)),new Integer(his_allNum+Background.testNum));
		}
		if(e.getSource()== btnReset){
			//this.dispose();
			JFreeChartshow chart=new JFreeChartshow("成绩统计图");
	        chart.pack();//以合适的大小显示
	        chart.setVisible(true);
			
		}
	}
}
