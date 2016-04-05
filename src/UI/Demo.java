package UI;



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
	JTextField text=new JTextField(20);
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
	public Demo()
	{
		super(new GridBagLayout());
		
		JMenuBar menubar = new JMenuBar();

	   	 JMenu NewMenu = new JMenu("任务创建");
	   	 JMenu UpdateMenu = new JMenu("升级管理");
	   	 JMenu HelpMenu = new JMenu("关于");
	   	 
	   	JMenuItem CreateOneMenu= new JMenuItem("新建任务");
       CreateOneMenu.addActionListener(new CreateOneActionListener());
       JMenuItem CreateMultiMenu= new JMenuItem("新建多个文件任务");
       CreateMultiMenu.addActionListener(new CreateMultiActionListener());
       JMenuItem ExitMenu= new JMenuItem("退出");
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
        
        String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };

 
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
            }  
        });
        
        // 创建选择日志文件按钮
         getfilepath = new JButton("...");
         getfilepath.addActionListener(new GetfilepathActionListener());
         //getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         //创建执行分析过程按钮
         Analysis = new JButton("确定");
         Analysis.addActionListener(new analysisActionListener());
         //Analysis.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
         String urlString="C://Users//dell//Desktop//论文答辩//log1.png";  
         JLabel label=new JLabel(new ImageIcon(urlString));
         
         JLabel info_1 = new JLabel("Huang Yanyu,All right reserved.");
         JLabel info_2 = new JLabel("China University of Geosciences,2016.");
         
         text.setPreferredSize(new Dimension (270,26));
         
 
        //布局，将控件都丢进Jpanel，panel作为一个容器使用
        //Jpanel是一个静态控制组件，可以用来显示一行静态信息，不能接受用户的输入。它必须放在象JFrame这样的顶级窗口上才能输出。
         /******
         GridBagLayout layout = new GridBagLayout();
         this.setLayout(layout);
         this.add(formatList);//把组件添加进jframe
         GridBagConstraints s_layout= new GridBagConstraints();
       s_layout.fill = GridBagConstraints.BOTH;
       s_layout.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
       s_layout.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
       s_layout.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
       layout.setConstraints(formatList, s_layout);//设置组件
       *****/
         GridBagLayout layout = new GridBagLayout();
         this.setLayout(layout);
         this.add(menubar);
         this.add(label);
         this.add(formatList);//把组件添加进jframe
         this.add(text);
         this.add(getfilepath);
         this.add(Analysis);
         this.add(info_1);
         this.add(info_2);
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
        
        s_layout.weightx = 1;
        layout.setConstraints(text, s_layout);

        s_layout.insets = new Insets(20,10,10,0);//	top, left,  bottom, right
        //s_layout.gridwidth=0;
        layout.setConstraints(getfilepath, s_layout);
        
        s_layout.insets = new Insets(20,10,10,10);
        s_layout.gridwidth=0;
        layout.setConstraints(Analysis, s_layout);
        
        s_layout.anchor= GridBagConstraints.WEST ;
        layout.setConstraints(info_1, s_layout);
        s_layout.insets = new Insets(0,10,10,20);
        layout.setConstraints(info_2, s_layout);
        s_layout.insets = new Insets(0,0,10,20);
        layout.setConstraints(menubar, s_layout);

        
        /***s_layout.gridwidth=1;
        layout.setConstraints(showcitychart, s_layout);
        s_layout.gridwidth=0;
        layout.setConstraints(showattacktable, s_layout);
        ***/
	}
	


//选择日志文件按钮监听内容
	private class GetfilepathActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("你按了按钮");   
            MyFile.showOpenDialog(null);  //弹出文件选择
            File f = MyFile.getSelectedFile();  //得到文件
            if(f != null){
                globleStatus.setFilename(f.getAbsolutePath());//把日志的绝对路径给全局状态
               text.setText(globleStatus.getFilename());
               //清空以往数据
               MiddleDataVector vector_clear = MiddleDataVector.getInstance();
               vector_clear.Clear();
           	judgement.num_sql = 0 ;
        	judgement.num_xss = 0 ;
        	judgement.num_exec = 0 ;
        	statistics.Clear();
            }
        } 
    }
	//执行分析过程按钮监听内容
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            //System.out.println("你按了确认");   

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
	
	private static class CreateOneActionListener implements ActionListener{  
	    public void actionPerformed(ActionEvent e) {  
	        //System.out.println("…");   
	       
	    } 
	}
	private static class CreateMultiActionListener implements ActionListener{  
	    public void actionPerformed(ActionEvent e) {  
	        //System.out.println("…");   
	       
	    } 
	}
    }
    



