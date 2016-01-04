package logic;

import java.awt.Font;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import gloable.MiddleDataVector;

public class chart{
	ChartPanel frame1;
	public void BarChart(MiddleDataVector vector){
		 CategoryDataset dataset = getDataSet();
		 //MiddleDataVector v = vector.getInstance();
	     JFreeChart chart = ChartFactory.createBarChart3D(  
	                            "ip���ڵ�ͳ��", // ͼ�����  
	                            "����", // Ŀ¼�����ʾ��ǩ  
	                            "����", // ��ֵ�����ʾ��ǩ  
	                            dataset, // ���ݼ�  
	                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ  
	                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)  
	                            false,          // �Ƿ����ɹ���  
	                            false           // �Ƿ�����URL����  
	                            );
	     CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������  
	     CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�  
	     domainAxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
	     domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����  
	     ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
	     rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
	     chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
	     chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
	            
	          //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������  
	            
	     frame1=new ChartPanel(chart,true);        //����Ҳ������chartFrame,����ֱ������һ��������Frame  
	}
	private CategoryDataset getDataSet() {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        dataset.addValue(100, "����", "");  
        dataset.addValue(100, "�Ϻ�", "");  
        dataset.addValue(100, "����", "");  
        dataset.addValue(200, "����", "");  
        dataset.addValue(200, "�Ϻ�", "");  
        dataset.addValue(200, "����", "");  
        dataset.addValue(300, "����", "");  
        dataset.addValue(300, "�Ϻ�", "");  
        dataset.addValue(300, "����", "");  
        dataset.addValue(400, "����", "");  
        dataset.addValue(400, "�Ϻ�", "");  
        dataset.addValue(400, "����", "");  
        dataset.addValue(500, "����", "");  
        dataset.addValue(500, "�Ϻ�", "");  
        dataset.addValue(500, "����", "");  
		return dataset;
	}
}
