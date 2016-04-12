package UI;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;

import javax.swing.JComboBox;

import javax.swing.JPanel;

import Gloable.MiddleDataVector;
import Gloable.globleStatus;
import Logic.judgement;
import Logic.statistics;


import java.awt.event.*;
import java.io.File;

import javax.swing.*;



public class Demo  extends JPanel{
	//JLabel filepath;//显示文本，图像，或两者兼而有
	
	JButton getfilepath;//获取文件路径
	JButton Analysis;//分析文件
	JButton showcitychart;//获取文件路径
	JButton showattacktable;//分析文件
	JTextField text=new JTextField(30);
	JRadioButton defaultpath;
	String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };
	
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
    public static String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    } 
	
	public Demo()
	{
		super(new BorderLayout());
		
		
		JMenuBar menubar = new JMenuBar();

	   	 JMenu NewMenu = new JMenu("任务创建");
	   	 JMenu UpdateMenu = new JMenu("升级管理");
	   	 JMenu HelpMenu = new JMenu("关于");
	   	 
	   	JMenuItem CreateOneMenu= new JMenuItem("新建任务");
       CreateOneMenu.addActionListener(new CreateoneActionListener());
       JMenuItem CreateMultiMenu= new JMenuItem("新建多个文件任务");
       CreateMultiMenu.addActionListener(new CreatemultiActionListener());
       JMenuItem ExitMenu= new JMenuItem("退出");
       ExitMenu.addActionListener(new ExitActionListener());
       JMenuItem UpdatePropertiesMenu= new JMenuItem("更新特征库");
       JMenuItem AboutMenu= new JMenuItem("版本信息");
       
       NewMenu.add(CreateOneMenu);
       NewMenu.add(CreateMultiMenu);
       NewMenu.add(ExitMenu);
       UpdateMenu.add(UpdatePropertiesMenu);
       HelpMenu.add(AboutMenu);
       
       menubar.add(NewMenu);
       menubar.add(UpdateMenu);
       menubar.add(HelpMenu);
        
        
        JPanel PathChoose = new JPanel();
 
        //创建 combo box选择日志格式, 默认为第1个选项.
        final JComboBox<String> formatList = new JComboBox<String>(formatStrings);
        formatList.setSelectedIndex(0);//设定默认显示的index
        formatList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formatList.setPreferredSize(new Dimension (100,48));
        globleStatus.setFileType(formatList.getSelectedIndex());
        
        //设置监听将选项，传入全局状态中的日志格式
        formatList.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e) {  
                int petName = formatList.getSelectedIndex();
                //System.out.printf("你选择了%d",petName);
                globleStatus.setFileType(petName);
                int i = globleStatus.getFileType();
                if(defaultpath.isSelected())
                	setRadioButton(i);
                	
            }  
        });
        
        // 创建选择日志文件按钮
         //getfilepath = new JButton("...");
        // getfilepath.addActionListener(new GetfilepathActionListener());
         //getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         //创建执行分析过程按钮
         Analysis = new JButton("确定");
         Analysis.addActionListener(new analysisActionListener());
         //Analysis.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
         String urlString="C://Users//dell//Desktop//论文答辩//log2.png";  
         JLabel label=new JLabel(new ImageIcon(urlString));
         
         JLabel info_1 = new JLabel("All right reserved.");
         JLabel info_2 = new JLabel("<html><p>注意事项:</p><p>&nbsp;&nbsp;&nbsp;&nbsp;在选取多个日志文件时,请确保文件夹中日志文件按时间排序.</p><html>");
         text.setPreferredSize(new Dimension (270,26));
         defaultpath = new JRadioButton("默认路径");
         defaultpath.addActionListener(new defaultActionListener());
 
        //布局，将控件都丢进Jpanel，panel作为一个容器使用
        //Jpanel是一个静态控制组件，可以用来显示一行静态信息，不能接受用户的输入。它必须放在象JFrame这样的顶级窗口上才能输出。

         GridBagLayout layout = new GridBagLayout();
        PathChoose.setLayout(layout);
         
        PathChoose.add(formatList);//把组件添加进jframe
        PathChoose.add(text);

        PathChoose.add(defaultpath);
        PathChoose.add(Analysis);
        PathChoose.add(info_2);
        PathChoose.add(info_1);
         //this.add(showattacktable);
         
         
        GridBagConstraints s_layout= new GridBagConstraints();
        
        s_layout.fill = GridBagConstraints.CENTER;
        
        s_layout.insets = new Insets(20,0,0,0);
        s_layout.gridwidth=0;
        layout.setConstraints(label, s_layout);
        
        s_layout.insets = new Insets(10,5,0,0);
        s_layout.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s_layout.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s_layout.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(formatList, s_layout);//设置组件
        
        s_layout.weightx = 0.8;
        layout.setConstraints(text, s_layout);
        layout.setConstraints(defaultpath, s_layout);

       // s_layout.insets = new Insets(20,10,10,0);//	top, left,  bottom, right
        //s_layout.gridwidth=0;
       // layout.setConstraints(getfilepath, s_layout);
        
        s_layout.insets = new Insets(20,10,10,10);
        s_layout.gridwidth=0;
        layout.setConstraints(Analysis, s_layout);
        
        s_layout.insets = new Insets(5,10,10,10);
        s_layout.anchor= GridBagConstraints.WEST ;
        layout.setConstraints(info_1, s_layout);
        layout.setConstraints(info_2, s_layout);
        
        this.add(menubar,BorderLayout.NORTH);
        this.add(label,BorderLayout.CENTER);
        this.add(PathChoose,BorderLayout.SOUTH);
	}
	
	public void setRadioButton(int i)
	{
		switch (i) {
		case 0:
			text.setText("C:\\WINDOWS\\system32\\Logfiles\\");
			break;
		case 1:
			text.setText("<安装目录>\\logs\\");
			break;
		case 2:
			text.setText("<安装目录>\\logs\\access.log文件");
			break;
		case 3:
			text.setText("<安装目录>\\logs\\");
			break;
		default:
			break;
		}
	}
	
	private class ExitActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	System.exit(0);
        } 
    }
	
	private class defaultActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	int i = globleStatus.getFileType();
        	if(defaultpath.isSelected())
        	    setRadioButton(i);
        	else{
        		text.setText(globleStatus.getFilename());
        	}
        } 
    }
	
	
//选择日志文件按钮监听内容
	private class CreateoneActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("你按了按钮");   
            MyFile.showOpenDialog(null);  //弹出文件选择
            File f = MyFile.getSelectedFile();  //得到文件
            if(f != null){
                globleStatus.setFilename(f.getAbsolutePath());//把日志的绝对路径给全局状态
               text.setText(globleStatus.getFilename());
               defaultpath.setSelected(false);
               //清空以往数据
               MiddleDataVector vector_clear = MiddleDataVector.getInstance();
               vector_clear.Clear();
           	   judgement.num_sql = 0 ;
        	   judgement.num_xss = 0 ;
        	   judgement.num_exec = 0 ;
               statistics.Clear();
            }
            globleStatus.multi = false;
        } 
    }
	private class CreatemultiActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("你按了按钮");   
        	MyFile.setMultiSelectionEnabled(true);
            MyFile.showOpenDialog(null);  //弹出文件选择
            File[] f = MyFile.getSelectedFiles();  //得到文件

            for(int i = 0;i < f.length;i ++){
            if(f != null ){
            	System.out.println(f[i].getAbsolutePath());   
                globleStatus.filenames.add(f[i].getAbsolutePath());//把日志的绝对路径给全局状态
               text.setText(text.getText()+";"+globleStatus.filenames.get(i));
            }
        }
          //清空以往数据
            MiddleDataVector vector_clear = MiddleDataVector.getInstance();
            vector_clear.Clear();
        	judgement.num_sql = 0 ;
     	    judgement.num_xss = 0 ;
     	    judgement.num_exec = 0 ;
     	    statistics.Clear();
            globleStatus.multi = true;
        }
    }
	//执行分析过程按钮监听内容
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            //System.out.println("你按了确认");  
            String theFilename;
        	if(globleStatus.multi)
        	{
        		 theFilename=globleStatus.filenames.get(0);//得到文件路径
        	}
        	else{
        		theFilename=globleStatus.getFilename();//得到文件路径
        	}
            boolean log = true;

            if(theFilename == null )
            {
            	JOptionPane.showMessageDialog(null, "请先选择日志文件!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
            }
            else 
            {
            	if(globleStatus.multi)
            	{
            		File[] f = MyFile.getSelectedFiles();
                	for(int i = 0;i < f.length;i ++)
                		if(!f[i].getName().endsWith(".log"))
                			log = false;
            		if(log)
            		{
            			MyDialog iDialog = new MyDialog();
            			iDialog.run();//run就是那个第一步第二步第三步的主流程
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "日志文件格式错误!", "错误信息",
                                JOptionPane.ERROR_MESSAGE);
					}
            	}
            	else {
            		File f = MyFile.getSelectedFile();
            		 if(f.getName().endsWith(".log"))
            		 {
            			 MyDialog iDialog = new MyDialog();
             			iDialog.run();//run就是那个第一步第二步第三步的主流程
            		 }
            		 else{
                     	JOptionPane.showMessageDialog(null, "日志文件格式错误!", "错误信息",
                                 JOptionPane.ERROR_MESSAGE);
                     }
				}
            }
            }
    }
    }
    



