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
        this.setContentPane(createPanel()); //���캯�����Զ�����Java��panel���
    }
    
    public static CategoryDataset createDataset() //������״ͼ���ݼ�
    {
    	
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(80,"a","��һ��");
        dataset.setValue(70,"b","�ڶ���");
        dataset.setValue(60,"c","������");
        dataset.setValue(75,"d","������");
        
        return dataset;
    }
    
    public static JFreeChart createChart(CategoryDataset dataset) //�����ݼ�����һ��ͼ��
    {
        JFreeChart chart=ChartFactory.createBarChart("hi", "����ͳ��", 
                "�÷�", dataset, PlotOrientation.VERTICAL, true, true, false); //����һ��JFreeChart
    
        chart.setTitle(new TextTitle("�ɼ�����ͼ",new Font("����",Font.BOLD+Font.ITALIC,20)));//�����������ñ��⣬�滻��hi������
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//���ͼ���м䲿�֣���plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//��ú�����
        categoryAxis.setLabelFont(new Font("΢���ź�",Font.BOLD,12));//���ú���������
        categoryAxis.setTickLabelFont(new Font("΢���ź�",Font.BOLD,12)); //x���±�
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״     
        rangeAxis.setLabelFont(new Font("΢���ź�",Font.BOLD,14)); //Y�����  
        return chart;
    }
    
    
    public static JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        return new ChartPanel(chart); //��chart�������Panel�����ȥ��ChartPanel���Ѽ̳�Jpanel
    }
    
//    public static void main(String[] args)
//    {
//        JFreeChartshow chart=new JFreeChartshow("�ɼ�ͳ��ͼ");
//        chart.pack();//�Ժ��ʵĴ�С��ʾ
//        chart.setVisible(true);
//        
//    }
}