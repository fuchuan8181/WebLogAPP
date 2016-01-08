package UI;


import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import javax.swing.JComboBox;

import javax.swing.JPanel;

import Gloable.globleStatus;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;



public class Demo extends JPanel {
	//JLabel filepath;//显示文本，图像，或两者兼而有
	
	JButton getfilepath;//获取文件路径
	JButton Analysis;//分析文件
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
	public Demo()
	{
        super(new BorderLayout());//调用父类的构造方法
        
        String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };

 
        //创建 combo box选择日志格式, 默认为第1个选项.
        final JComboBox<String> formatList = new JComboBox<String>(formatStrings);
        formatList.setSelectedIndex(0);//设定默认显示的index
        globleStatus.setFileType(formatList.getSelectedIndex());
        
        //设置监听将选项，传入全局状态中的日志格式
        formatList.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e) {  
                int petName = formatList.getSelectedIndex();
                //System.out.printf("你选择了%d",petName);
                globleStatus.setFileType(petName);
            }  
        });
        
        // 创建选择日志文件按钮
         getfilepath = new JButton("选择日志文件");
         getfilepath.addActionListener(new GetfilepathActionListener());
         getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         //创建执行分析过程按钮
         Analysis = new JButton("载入并分析");
         Analysis.addActionListener(new analysisActionListener());
         Analysis.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
 
        //暂时没有用
        /*filepath = new JLabel();
        filepath.setFont(filepath.getFont().deriveFont(Font.ITALIC));//设置字体大小
        filepath.setHorizontalAlignment(JLabel.CENTER);//设置标签内容沿 X 轴的对齐方式

        filepath.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));//创建一个占用空间但没有绘制的空边框，指定了顶线、底线、左边框线和右边框线的宽度。
        filepath.setPreferredSize(new Dimension(177, 122));//设置此组件的首选大小。
        */
 
        //布局，将控件都丢进Jpanel，panel作为一个容器使用
        //Jpanel是一个静态控制组件，可以用来显示一行静态信息，不能接受用户的输入。它必须放在象JFrame这样的顶级窗口上才能输出。
        add(formatList, BorderLayout.PAGE_START);//组件出现在第一行布局内容之前
        add(getfilepath,BorderLayout.CENTER);
        add(Analysis,BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
	}
	


//选择日志文件按钮监听内容
	private class GetfilepathActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("你按了按钮");   
            MyFile.showOpenDialog(null);  //弹出文件选择
            File f = MyFile.getSelectedFile();  //得到文件
            if(f != null){
                globleStatus.setFilename(f.getAbsolutePath());//把日志的绝对路径给全局状态
               
            }
        } 
    }
	//执行分析过程按钮监听内容
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("你按了确认");   

            String theFilename=globleStatus.getFilename();//得到文件路径
            if(theFilename != null)
            {
                MyDialog iDialog = new MyDialog();
                iDialog.run();//run就是那个第一步第二步第三步的主流程
            }
            else //若文件未选择或不存在则显示错误信息
            {
            	 JOptionPane.showMessageDialog(null, "请先选择日志文件!", "错误信息",
                         JOptionPane.ERROR_MESSAGE);
            }
                
            }

    }


    }
    



