package background;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Fraction.Fractions;
import caculate.Caculate;

// 后台事件处理类
public class Background {
	private static int diffculty = 10; // 定义难度系数，来确定是多大数量的分数运算
	public static int testNum = 10; // 定义题目数量
	private String[] result = new String[testNum]; // 储存生成题目的结果
	private String[] question_1 = new String[testNum]; // 储存每个题目第一个待运算数
	private String[] question_2 = new String[testNum]; // 储存每个题目第二个待运算数
	private String[] operator = new String[testNum]; // 储存每个题目的运算符
	private ArrayList<String> scoresList = new ArrayList<String>(); // 定义错题集
	private BufferedWriter writer;

	public Background() {

	}

	public void createTest() {
		// 定义一个随机数
		Random random = new Random();
		/*
		 * 定义两个个Fractions对象，分别为： 第一个运算数 第二个运算数
		 */
		Fractions fractions_1 = new Fractions();
		Fractions fractions_2 = new Fractions();
		// 定义Caculate对象用于计算结果
		Caculate caculate = new Caculate();
		// 生成10道题
		for (int i = 0; i < testNum; i++) {
			// 分别随机 生成第一个和第二个运算数并储存到question字符串集里
			fractions_1.setValue(random.nextInt(diffculty) + 1, random.nextInt(diffculty) + 1);
			question_1[i] = fractions_1.getFraction();
			fractions_2.setValue(random.nextInt(diffculty) + 1, random.nextInt(diffculty) + 1);
			question_2[i] = fractions_2.getFraction();
			caculate.setOperationNum(fractions_1, fractions_2);
			// 随机生成符号并计算结果存储到result字符串集
			switch (random.nextInt(4)) {
			// 加法运算
			case 0:
				operator[i] = "+"; // 储存运算符
				result[i] = caculate.addtion().getFraction(); // 获取运算结果
				break;
			// 减法运算
			case 1:
				operator[i] = "-";
				result[i] = caculate.subtraction().getFraction();
				break;
			// 乘法运算
			case 2:
				operator[i] = "*";
				result[i] = caculate.multiplication().getFraction();
				break;
			// 除法运算
			case 3:
				operator[i] = "÷";
				result[i] = caculate.division().getFraction();
				break;
			}
		}
	}

	// 传入一组答案,校验答案
	public String[] checkAnswer(String[] answers) {
		for (int i = 0; i < testNum; i++) {
			// 如果答错了记下题号
			if (!result[i].equals(answers[i])) {
				scoresList.add(new Integer(i + 1).toString());
			}
		}
		String[] str = new String[scoresList.size()];
		scoresList.toArray(str);
		return str; // 返回一个得分集
	}

	// 获取完整的题目
	public String[] getQuestions() {
		String[] questions = new String[testNum];
		for (int i = 0; i < testNum; i++)
			questions[i] = question_1[i] + "   " + operator[i] + "   " + question_2[i] + "  =  ";
		return questions;
	}

	// 获取标准答案
	public String[] getStandardAnswer() {
		return result;
	}
		// 上传正确率
	public void upDate(Integer right,Integer all){
		try {
			writer = new BufferedWriter(new FileWriter(new File("history/accuracy.txt")));
			writer.write(right.toString());
			writer.newLine();
			writer.write(all.toString());
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}