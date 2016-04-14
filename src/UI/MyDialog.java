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
		
		//Frame������ҳ����Ҳ�����������������壬Ҫ��ѿؼ����ڸý����У�����ѿؼ�����JPanel��
		//Ϊ��ײ㣬ͬһ������ֻ��һ��JFrame
		
        JFrame frame = new JFrame("Web��־���ϵͳ");//������
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ָ���رհ�ť�˳�Ӧ�ó���
 
        //Create and set up the content pane.
        JComponent newContentPane = new Demo();//ʵ����������������
        newContentPane.setOpaque(true); //���ÿؼ���͸��
        frame.setContentPane(newContentPane);//JFrame�ǿ�ܣ�����ֱ����ӿؼ�������Jpanel���м�ؼ�
 
        //Display the window.
        frame.pack();
        //frame.setSize(400,400);
        frame.setVisible(true);//��Ϊ�ɼ�
    }
	
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
