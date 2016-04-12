package Chart;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Logic.statistics;

public class barChart {
	public void getBarChart(){
		CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(//createBarChart("����IP��������", "��������", "��������", dataset);createBarChart3D(
        		"����IP��ַλ�÷ֲ�", // ͼ�����
                "��������", // Ŀ¼�����ʾ��ǩ
                "��������", // ��ֵ�����ʾ��ǩ
                dataset, // ���ݼ�
                PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                true,  // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
                true,  // �Ƿ����ɹ���
                false  // �Ƿ�����URL����
                );
        //�����￪ʼ  
        CategoryPlot plot=chart.getCategoryPlot();//��ȡͼ���������  
        CategoryAxis domainAxis=plot.getDomainAxis();         //ˮƽ�ײ��б�  
        domainAxis.setLabelFont(new Font("����",Font.BOLD, 14));         //ˮƽ�ײ�����  
        domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����  
        ValueAxis rangeAxis=plot.getRangeAxis();//��ȡ��״  
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
        chart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
        //�������������Ȼ�����е�࣬��ֻΪһ��Ŀ�ģ����������������  
        /*try {
          File file = new File("c:/student.png");
          ChartUtilities.saveChartAsPNG(file,chart,200,150);//�ѱ�����Ϊ�ļ�
        }catch (Exception e) {
                  String s = e.getLocalizedMessage();
                  s = e.getMessage();
                  s = e.toString();
        }*/
        //�����ɵı���ŵ�Ԥ��������
        final ChartFrame preview = new ChartFrame("����IP��ַλ�÷ֲ�",chart);
     	preview.addWindowListener(new WindowAdapter() {
     			public void windowClosing(final WindowEvent event) {
     			preview.dispose();}});
     	preview.pack();
     	//����Ԥ�����ڵĴ�С��λ��,�ʺ���Ļ�����Ҿ���
     	/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     	preview.setSize(screenSize.width,screenSize.height-50);//�ʺ���Ļ��50��ʾ�ѹ�����Ҫ��������
     	Dimension frameSize = preview.getSize();
     	if (frameSize.height > screenSize.height) {
       	frameSize.height = screenSize.height;
     	}
     	if (frameSize.width > screenSize.width) {

       	frameSize.width = screenSize.width;
     	}
     	preview.setLocation( (screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height-50) / 2);*/
     	preview.setVisible(true);//��ʾ����Ԥ������
	} 
	private CategoryDataset getDataSet() {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		
		Map<String, Integer> sqli = statistics.sql_arr;
		Map<String, Integer> xss = statistics.xss_arr;	
		Map<String, Integer> exec = statistics.exec_arr;
		
		for (Map.Entry<String, Integer> entry : sqli.entrySet()) {  
			dataset.addValue( entry.getValue(), entry.getKey(), "SQLi");
		}
		for (Map.Entry<String, Integer> entry : xss.entrySet()) {  
			dataset.addValue( entry.getValue(), entry.getKey(), "XSS");
		}
		for (Map.Entry<String, Integer> entry : exec.entrySet()) {  
			dataset.addValue( entry.getValue(), entry.getKey(), "Exec");
		}

        return dataset;
	}
}
