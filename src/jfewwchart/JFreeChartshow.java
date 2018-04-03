package jfewwchart;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import background.Background;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class JFreeChartshow extends ApplicationFrame {
	private static Font FONT = new Font("����", Font.PLAIN, 20);
	private static ArrayList<String> scores = new ArrayList<String>();
	static {
		try {
			loadScores();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JFreeChartshow(String title) {
		super(title);
		this.setContentPane(createPanel()); // ���캯�����Զ�����Java��panel���
	}

	private static void loadScores() throws IOException {
		File file = new File("history/scores.txt");
		if (file.exists()) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			while (true) {
				String readLine = reader.readLine();
				if (readLine == null) {
					break;
				}
				scores.add(readLine);
			}
			reader.close();
		} else {
			file.createNewFile();
		}
	}

	public static CategoryDataset createDataset() // ������״ͼ���ݼ�
	{

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int i = 0;
		for (String str : scores) {
			i++;
			String[] split = str.split("-----");
			dataset.addValue(Integer.parseInt(split[1]), "�÷�", "��" + i + "��");
		}

		return dataset;
	}

	public static JFreeChart createChart() // �����ݼ�����һ��ͼ��
	{
		JFreeChart chart = ChartFactory.createBarChart("����ͳ��ͼ", "����ͳ��", "�÷�",
				createDataset(), PlotOrientation.VERTICAL, false, true, false); // ����һ��JFreeChart
		
		chart.setBorderPaint(Color.blue);
		chart.setBackgroundPaint(Color.lightGray);
		chart.setTitle(new TextTitle("�ɼ�����ͼ", FONT));// �����������ñ��⣬
		CategoryPlot plot = (CategoryPlot) chart.getPlot();// ���ͼ���м䲿�֣���plot
		plot.setDomainCrosshairPaint(Color.orange);
		CategoryAxis categoryAxis = plot.getDomainAxis();// ��ú�����
		categoryAxis.setLabelFont(FONT);// ���ú���������
		categoryAxis.setTickLabelFont(FONT); // x���±�
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// �����ǩ��45����б
		ValueAxis rangeAxis = plot.getRangeAxis();// ��ȡ��״
		rangeAxis.setLabelFont(FONT); // Y�����
		
		// plot.setBackgroundPaint(Color.orange);//ͼƬ����
		

		return chart;
	}

	public static JPanel createPanel() {
		JFreeChart chart = createChart();
		return new ChartPanel(chart); // ��chart�������Panel�����ȥ��ChartPanel���Ѽ̳�Jpanel
	}

	public static void main(String[] args) {
		JFreeChartshow chart = new JFreeChartshow("�ɼ�ͳ��ͼ");
		chart.pack();// �Ժ��ʵĴ�С��ʾ
		chart.setVisible(true);

	}
}