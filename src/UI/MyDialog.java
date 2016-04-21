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
