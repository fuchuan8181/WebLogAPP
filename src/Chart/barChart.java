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
import Logic.statistics;;

public class barChart {
	public void getBarChart(){
		CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(//createBarChart("攻击IP所属城市", "攻击类型", "攻击次数", dataset);createBarChart3D(
        		"攻击IP地址位置分布", // 图表标题
                "攻击类型", // 目录轴的显示标签
                "攻击次数", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,  // 是否显示图例(对于简单的柱状图必须是false)
                true,  // 是否生成工具
                false  // 是否生成URL链接
                );
        //从这里开始  
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象  
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表  
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD, 14));         //水平底部标题  
        domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状  
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
        chart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题  
        /*try {
          File file = new File("c:/student.png");
          ChartUtilities.saveChartAsPNG(file,chart,200,150);//把报表保存为文件
        }catch (Exception e) {
                  String s = e.getLocalizedMessage();
                  s = e.getMessage();
                  s = e.toString();
        }*/
        //将生成的报表放到预览窗口中
        final ChartFrame preview = new ChartFrame("攻击IP地址位置分布",chart);
     	preview.addWindowListener(new WindowAdapter() {
     			public void windowClosing(final WindowEvent event) {
     			preview.dispose();}});
     	preview.pack();
     	//调整预览窗口的大小和位置,适合屏幕，并且居中
     	/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     	preview.setSize(screenSize.width,screenSize.height-50);//适合屏幕，50表示把工具栏要考虑在内
     	Dimension frameSize = preview.getSize();
     	if (frameSize.height > screenSize.height) {
       	frameSize.height = screenSize.height;
     	}
     	if (frameSize.width > screenSize.width) {

       	frameSize.width = screenSize.width;
     	}
     	preview.setLocation( (screenSize.width - frameSize.width) / 2,(screenSize.height - frameSize.height-50) / 2);*/
     	preview.setVisible(true);//显示报表预览窗口
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
			//System.out.println(city);
			/*dataset.addValue(100, "苹果", "苹果");  
        	dataset.addValue(200, "梨子", "梨子");  
        	dataset.addValue(300, "葡萄", "葡萄");  
        	dataset.addValue(400, "香蕉", "香蕉");  
        	dataset.addValue(500, "荔枝", "荔枝");
			dataset.addValue(14, city, "SQLi"); 
			dataset.addValue(9, city, "XSS"); 
			dataset.addValue(9, city, "DoS");
			dataset.addValue(21, "天津市", "SQLi");
			dataset.addValue(4, "天津市", "XSS");
			dataset.addValue(4, "天津市", "DoS");
			dataset.addValue(6, "上海市", "SQLi");
			dataset.addValue(12, "上海市", "XSS");
			dataset.addValue(12, "上海市", "DoS");
			dataset.addValue(17, "北京市", "SQLi");
			dataset.addValue(11, "北京市", "XSS");
			dataset.addValue(11, "北京市", "DoS");*/
        return dataset;
	}
}
