package background;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Fraction.Fractions;
import caculate.Caculate;

// ��̨�¼�������
public class Background {
	private static int diffculty = 10; // �����Ѷ�ϵ������ȷ���Ƕ�������ķ�������
	public static int testNum = 10; // ������Ŀ����
	private String[] result = new String[testNum]; // ����������Ŀ�Ľ��
	private String[] question_1 = new String[testNum]; // ����ÿ����Ŀ��һ����������
	private String[] question_2 = new String[testNum]; // ����ÿ����Ŀ�ڶ�����������
	private String[] operator = new String[testNum]; // ����ÿ����Ŀ�������
	private ArrayList<String> scoresList = new ArrayList<String>(); // ������⼯
	private BufferedWriter writer;

	public Background() {

	}

	public void createTest() {
		// ����һ�������
		Random random = new Random();
		/*
		 * ����������Fractions���󣬷ֱ�Ϊ�� ��һ�������� �ڶ���������
		 */
		Fractions fractions_1 = new Fractions();
		Fractions fractions_2 = new Fractions();
		// ����Caculate�������ڼ�����
		Caculate caculate = new Caculate();
		// ����10����
		for (int i = 0; i < testNum; i++) {
			// �ֱ���� ���ɵ�һ���͵ڶ��������������浽question�ַ�������
			fractions_1.setValue(random.nextInt(diffculty) + 1, random.nextInt(diffculty) + 1);
			question_1[i] = fractions_1.getFraction();
			fractions_2.setValue(random.nextInt(diffculty) + 1, random.nextInt(diffculty) + 1);
			question_2[i] = fractions_2.getFraction();
			caculate.setOperationNum(fractions_1, fractions_2);
			// ������ɷ��Ų��������洢��result�ַ�����
			switch (random.nextInt(4)) {
			// �ӷ�����
			case 0:
				operator[i] = "+"; // ���������
				result[i] = caculate.addtion().getFraction(); // ��ȡ������
				break;
			// ��������
			case 1:
				operator[i] = "-";
				result[i] = caculate.subtraction().getFraction();
				break;
			// �˷�����
			case 2:
				operator[i] = "*";
				result[i] = caculate.multiplication().getFraction();
				break;
			// ��������
			case 3:
				operator[i] = "��";
				result[i] = caculate.division().getFraction();
				break;
			}
		}
	}

	// ����һ���,У���
	public String[] checkAnswer(String[] answers) {
		for (int i = 0; i < testNum; i++) {
			// �������˼������
			if (!result[i].equals(answers[i])) {
				scoresList.add(new Integer(i + 1).toString());
			}
		}
		String[] str = new String[scoresList.size()];
		scoresList.toArray(str);
		return str; // ����һ���÷ּ�
	}

	// ��ȡ��������Ŀ
	public String[] getQuestions() {
		String[] questions = new String[testNum];
		for (int i = 0; i < testNum; i++)
			questions[i] = question_1[i] + "   " + operator[i] + "   " + question_2[i] + "  =  ";
		return questions;
	}

	// ��ȡ��׼��
	public String[] getStandardAnswer() {
		return result;
	}
		// �ϴ���ȷ��
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