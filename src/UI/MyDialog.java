package UI;



import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Chart.barChart;
import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.globleStatus;
import Logic.LogReadIFace;
import Logic.ObjectFactory;
import Logic.doc_Properties;
import Logic.judgement;

public class MyDialog extends JPanel {


	public static void createAndShowGUI() {
        //Create and set up the window.
		
		//Frame是做主页面框架也可以用来做顶级窗体，要想把控件放在该界面中，必须把控件放在JPanel中
		//为最底层，同一个界面只有一个JFrame
		
        JFrame frame = new JFrame("Web日志审计系统");//构造框架
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//指定关闭按钮退出应用程序？
 
        //Create and set up the content pane.
        JComponent newContentPane = new Demo();//实例化并载入具体设计
        newContentPane.setOpaque(true); //设置控件不透明
        frame.setContentPane(newContentPane);//JFrame是框架，不能直接添加控件，建立Jpanel的中间控件
 
        //Display the window.
        frame.pack();
        //frame.setSize(400,400);
        frame.setVisible(true);//设为可见
    }
	
	public void run()
	{

		String fileName="";
		int fileType;
		/*********************
		 * 第一步：日志数据读取且分析
		 * 输入：文件名和格式
		 * 输出：中间格式数据-存储到一定的中间结果存储区域
		 * *******************/

		fileName=globleStatus.getFilename();
		fileType=globleStatus.getFileType();

	
		LogReadIFace obj=ObjectFactory.getLogFileOperationInstance(fileType);
		if(globleStatus.multi)
		{
			for(int i = 0;i < globleStatus.filenames.size();i ++)
			{
				obj.readAndAnalysis(globleStatus.filenames.get(i)); 
			}
		}
		else {
			obj.readAndAnalysis(fileName); 
		}



		/*********************
		 * 第二步：日志数据读取且分析
		 * 输入：中间格式数据
		 * 输出：分析的结果 
		 * *******************/
		
		doc_Properties doc = new doc_Properties();
		doc.get_Properties();

		/*for(int j = 0;j < globleStatus.regex_num;j ++){
			System.out.println(globleStatus.attack_regex[j]);
			}*/
		
		judgement ijudge = new judgement();
		ijudge.Attackjudgment();
		
		//barChart chart = new barChart();
		//chart.getBarChart();
		//new table();
		/***
		MiddleDataVector instance=MiddleDataVector.getInstance();
		for (int i=0;i<instance.m_element.size();i++)
		{
			//有多条记录，每条记录单独执行一次循环体
			LogDataItem lItem=(LogDataItem)(instance.m_element.get(i));
		}

		/*********************
		 * 第三步：结果输出
		 * 输入：分析的结果
		 * 输出：根据输出类型来决定所做的输出 
		 * *******************/
		globleStatus.getresult = true;

	}

}
