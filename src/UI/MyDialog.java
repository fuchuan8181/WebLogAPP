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
		 * ��һ������־���ݶ�ȡ�ҷ���
		 * ���룺�ļ����͸�ʽ
		 * ������м��ʽ����-�洢��һ�����м����洢����
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
		 * �ڶ�������־���ݶ�ȡ�ҷ���
		 * ���룺�м��ʽ����
		 * ����������Ľ�� 
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
			//�ж�����¼��ÿ����¼����ִ��һ��ѭ����
			LogDataItem lItem=(LogDataItem)(instance.m_element.get(i));
		}

		/*********************
		 * ��������������
		 * ���룺�����Ľ��
		 * ������������������������������� 
		 * *******************/
		globleStatus.getresult = true;

	}

}
