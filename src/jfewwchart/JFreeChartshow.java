package jfewwchart;
import java.awt.Font;
import java.awt.List;

import javax.swing.JPanel;





import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
public class JFreeChartshow extends ApplicationFrame
{
	
	 
    public JFreeChartshow(String title)
    {
        super(title);
        this.setContentPane(createPanel()); //构造函数中自动创建Java的panel面板
    }
    
    public static CategoryDataset createDataset() //创建柱状图数据集
    {
    	
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(80,"a","第一轮");
        dataset.setValue(70,"b","第二轮");
        dataset.setValue(60,"c","第三轮");
        dataset.setValue(75,"d","第四轮");
        
        return dataset;
    }
    
    public static JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        JFreeChart chart=ChartFactory.createBarChart("hi", "分数统计", 
                "得分", dataset, PlotOrientation.VERTICAL, true, true, false); //创建一个JFreeChart
    
        chart.setTitle(new TextTitle("成绩分析图",new Font("宋体",Font.BOLD+Font.ITALIC,20)));//可以重新设置标题，替换“hi”标题
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        categoryAxis.setTickLabelFont(new Font("微软雅黑",Font.BOLD,12)); //x轴下标
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状     
        rangeAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,14)); //Y轴标题  
        return chart;
    }
    
    
    public static JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        return new ChartPanel(chart); //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
    }
    
//    public static void main(String[] args)
//    {
//        JFreeChartshow chart=new JFreeChartshow("成绩统计图");
//        chart.pack();//以合适的大小显示
//        chart.setVisible(true);
//        
//    }
}