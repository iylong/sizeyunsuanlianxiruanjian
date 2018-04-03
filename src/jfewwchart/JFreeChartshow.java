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
	private static Font FONT = new Font("宋体", Font.PLAIN, 20);
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
		this.setContentPane(createPanel()); // 构造函数中自动创建Java的panel面板
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

	public static CategoryDataset createDataset() // 创建柱状图数据集
	{

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int i = 0;
		for (String str : scores) {
			i++;
			String[] split = str.split("-----");
			dataset.addValue(Integer.parseInt(split[1]), "得分", "第" + i + "次");
		}

		return dataset;
	}

	public static JFreeChart createChart() // 用数据集创建一个图表
	{
		JFreeChart chart = ChartFactory.createBarChart("分数统计图", "分数统计", "得分",
				createDataset(), PlotOrientation.VERTICAL, false, true, false); // 创建一个JFreeChart
		
		chart.setBorderPaint(Color.blue);
		chart.setBackgroundPaint(Color.lightGray);
		chart.setTitle(new TextTitle("成绩分析图", FONT));// 可以重新设置标题，
		CategoryPlot plot = (CategoryPlot) chart.getPlot();// 获得图标中间部分，即plot
		plot.setDomainCrosshairPaint(Color.orange);
		CategoryAxis categoryAxis = plot.getDomainAxis();// 获得横坐标
		categoryAxis.setLabelFont(FONT);// 设置横坐标字体
		categoryAxis.setTickLabelFont(FONT); // x轴下标
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// 分类标签以45度倾斜
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(FONT); // Y轴标题
		
		// plot.setBackgroundPaint(Color.orange);//图片背景
		

		return chart;
	}

	public static JPanel createPanel() {
		JFreeChart chart = createChart();
		return new ChartPanel(chart); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
	}

	public static void main(String[] args) {
		JFreeChartshow chart = new JFreeChartshow("成绩统计图");
		chart.pack();// 以合适的大小显示
		chart.setVisible(true);

	}
}