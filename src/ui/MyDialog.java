package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import gloable.LogDataItem;
import gloable.MiddleDataVector;
import gloable.globleStatus;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.IISLog;
import logic.LogReadIFace;
import logic.ObjectFactory;

public class MyDialog extends JPanel {
	javax.swing.JButton m_btn1=new javax.swing.JButton();
	javax.swing.JButton m_btn2=new javax.swing.JButton();
	public MyDialog()
	{
		/*this.setSize(600, 400);
		m_btn1.setLocation(10, 100);
		m_btn1.setSize(100, 30);
		m_btn2.setLocation(10, 100);
		m_btn2.setSize(100, 30);
		this.add(m_btn1);
		this.add(m_btn2);
		m_btn1.setVisible(true);
		m_btn2.setVisible(true); */
	}
	

	
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
        frame.pack();//设置 Frame 为可以容纳所有组件的最小尺寸
        frame.setVisible(true);//设为可见
    }
	
	public void run()
	{
		System.out.println("run is ok");
		String fileName="";
		int fileType;
		/*********************
		 * 第一步：日志数据读取且分析
		 * 输入：文件名和格式
		 * 输出：中间格式数据-存储到一定的中间结果存储区域
		 * *******************/
		globleStatus iglobleStatus = new globleStatus();
		fileName=iglobleStatus.getFilename();
		fileType=iglobleStatus.getFileType();
		
		System.out.printf("%s and %d",fileName,fileType);
		
		LogReadIFace obj=ObjectFactory.getLogFileOperationInstance(fileType);
		obj.readAndAnalysis(fileName); 
		System.out.println("analysis is ok");

		/*********************
		 * 第二步：日志数据读取且分析
		 * 输入：中间格式数据
		 * 输出：分析的结果 
		 * *******************/
		MiddleDataVector instance=MiddleDataVector.getInstance();
		for (int i=0;i<instance.m_element.size();i++)
		{
			//有多条记录，每条记录单独执行一次循环体
			LogDataItem lItem=(LogDataItem)(instance.m_element.get(i));
			if (lItem.bSQL)
			{
				
			}
			if (lItem.bZHAN)
			{
				
			}
		}

		/*********************
		 * 第三步：结果输出
		 * 输入：分析的结果
		 * 输出：根据输出类型来决定所做的输出 
		 * *******************/
	}

}
