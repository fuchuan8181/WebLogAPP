package ui;


import java.awt.BorderLayout;

import gloable.globleStatus;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;



public class Demo extends JPanel {
	JLabel filepath;//显示文本，图像，或两者兼而有
	
	JButton getfilepath;//获取文件路径
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
	public Demo()
	{
        super(new BorderLayout());//调用父类的构造方法
        
        String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };
        final globleStatus iglobleStatus = new globleStatus();
 
        //创建 combo box, 默认为第4个选项.

        final JComboBox formatList = new JComboBox(formatStrings);
        formatList.setSelectedIndex(3);//设定默认显示的index
        iglobleStatus.setFileType(formatList.getSelectedIndex());
        
        //设置监听将选项，传入全局状态中的日志格式
        formatList.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e) {  
                int petName = formatList.getSelectedIndex();
                //System.out.printf("你选择了%d",petName);
                iglobleStatus.setFileType(petName);
            }  
        });
        
        // 创建按钮
         getfilepath = new JButton("选择日志文件");
         getfilepath.addActionListener(new GetfilepathActionListener());
         getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
 
        //暂时没有用
        filepath = new JLabel();
        filepath.setFont(filepath.getFont().deriveFont(Font.ITALIC));//设置字体大小
        filepath.setHorizontalAlignment(JLabel.CENTER);//设置标签内容沿 X 轴的对齐方式

        filepath.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));//创建一个占用空间但没有绘制的空边框，指定了顶线、底线、左边框线和右边框线的宽度。
        filepath.setPreferredSize(new Dimension(177, 122));//设置此组件的首选大小。
 
        //布局，将控件都丢进Jpanel，panel作为一个容器使用
        //Jpanel是一个静态控制组件，可以用来显示一行静态信息，不能接受用户的输入。它必须放在象JFrame这样的顶级窗口上才能输出。
        add(formatList, BorderLayout.PAGE_START);//组件出现在第一行布局内容之前
        add(getfilepath,BorderLayout.CENTER);
        add(filepath, BorderLayout.PAGE_END);//组件出现在最后一行布局内容之后
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
	}
	


//按钮监听内容
	private class GetfilepathActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("你按了按钮");   
            MyFile.showOpenDialog(null);  //弹出文件选择
            File f = MyFile.getSelectedFile();  //得到
            if(f != null){
                globleStatus IglobleStatus = new globleStatus();
                IglobleStatus.setFilename(f.getAbsolutePath());//把日志的绝对路径给全局状态
                
                MyDialog iDialog = new MyDialog();
                iDialog.run();//run就是那个第一步第二步第三步的主流程
            }
        } 
    }


    }
    



