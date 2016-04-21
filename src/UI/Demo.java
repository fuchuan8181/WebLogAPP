package UI;



import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.ResultVector;
import Gloable.execattacklist;
import Gloable.globleStatus;
import Gloable.sqlattacklist;
import Gloable.xssattacklist;
import Logic.ResultSave;
import Logic.judgement;
import Logic.statistics;


import java.awt.event.*;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;



public class Demo  extends JFrame{
	//JLabel filepath;//显示文本，图像，或两者兼而有
	
	final static JComboBox<String> yearList2 = new JComboBox<String>();
	final static JComboBox<String> monthList2 = new JComboBox<String>();
	final static JComboBox<String> dayList2 = new JComboBox<String>();
	static JComboBox<String> hourList2 = new JComboBox<String>();
	static JComboBox<String> minuteList2 = new JComboBox<String>();
	static JComboBox<String> secondList2 = new JComboBox<String>();
	
	final static JComboBox<String> yearList = new JComboBox<String>();
	final static JComboBox<String> monthList = new JComboBox<String>();
	final static JComboBox<String> dayList = new JComboBox<String>();
	static JComboBox<String> hourList = new JComboBox<String>();
	static JComboBox<String> minuteList = new JComboBox<String>();
	static JComboBox<String> secondList = new JComboBox<String>();
	JDialog time = new JDialog();
	
	static Date fromtime;
	static Date totime;
	
	String ipstring = null;
	String keystring = null;
	int ipnum = 0;
	int timenum = 0;
	int keynum = 0;
	JButton Analysis;//分析文件

	JTextField text=new JTextField(30);
	JRadioButton defaultpath;
	String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };
	
    String urlString="picture//log1.png";  
    JLabel label=new JLabel(new ImageIcon(urlString));
    
    JLabel info_1 = new JLabel("All right reserved.");
    
    JLabel info_2 = new JLabel("<html><p>注意事项:</p><p>&nbsp;&nbsp;&nbsp;&nbsp;在选取多个日志文件时,请确保文件夹中日志文件按时间排序.</p><html>");
    JLabel info_3 = new JLabel("       ");
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	MiddleDataVector vector = MiddleDataVector.getInstance();
	
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
        this.setTitle("Web日志审计系统");//构造框架
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//指定关闭按钮退出应用程序？


		ImageIcon bg = new ImageIcon("picture//mainbackground.png");
		JLabel imgLabel = new JLabel(bg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgLabel.setBounds(0,0,bg.getIconWidth(), bg.getIconHeight());//设置背景标签的位置
        
        Container MainPanel=this.getContentPane();  
		
		
		JMenuBar menubar = new JMenuBar();

	   	 JMenu NewMenu = new JMenu("任务创建");
	   	 JMenu SearchMenu = new JMenu("查找模块");
	   	JMenu DispalyMenu = new JMenu("显示结果");
	   	 JMenu UpdateMenu = new JMenu("升级管理");
	   	 JMenu HelpMenu = new JMenu("关于");
	   	 
	   	JMenuItem CreateOneMenu= new JMenuItem("新建任务");
       CreateOneMenu.addActionListener(new CreateoneActionListener());
       JMenuItem CreateMultiMenu= new JMenuItem("新建多个文件任务");
       CreateMultiMenu.addActionListener(new CreatemultiActionListener());
       JMenuItem ExitMenu= new JMenuItem("退出");
       ExitMenu.addActionListener(new ExitActionListener());
       
       JMenuItem FollowKeyMenu= new JMenuItem("按关键字查找");
       FollowKeyMenu.addActionListener(new FollowKeyActionListener());
       JMenuItem FollowTimeMenu= new JMenuItem("按时间查找");
       FollowTimeMenu.addActionListener(new FollowTimeActionListener());
       JMenuItem FollowIPMenu= new JMenuItem("按IP地址查找");
       FollowIPMenu.addActionListener(new FollowIPActionListener());
       
       JMenuItem AllResultMenu= new JMenuItem("显示检测结果");
       AllResultMenu.addActionListener(new AllResultActionListener());
       JMenuItem SaveResultMenu= new JMenuItem("保存检测结果");
       SaveResultMenu.addActionListener(new SaveActionListener());
       JMenuItem UpdatePropertiesMenu= new JMenuItem("更新特征库");
       JMenuItem AboutMenu= new JMenuItem("版本信息");
       AboutMenu.addActionListener(new AboutActionListener());
       
       NewMenu.add(CreateOneMenu);
       NewMenu.add(CreateMultiMenu);
       NewMenu.add(ExitMenu);
       
       SearchMenu.add(FollowKeyMenu);
       SearchMenu.add(FollowTimeMenu);
       SearchMenu.add(FollowIPMenu);
       
       DispalyMenu.add(AllResultMenu);
       DispalyMenu.add(SaveResultMenu);
       
       
       UpdateMenu.add(UpdatePropertiesMenu);
       HelpMenu.add(AboutMenu);
       
       menubar.add(NewMenu);
       menubar.add(SearchMenu);
       menubar.add(DispalyMenu);
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
        PathChoose.add(info_3);
        PathChoose.add(info_2);
        PathChoose.add(info_1);
         //this.add(showattacktable);
         
         
        GridBagConstraints s_layout= new GridBagConstraints();
        
        s_layout.fill = GridBagConstraints.CENTER;
        
        s_layout.insets = new Insets(20,0,0,0);
        s_layout.gridwidth=0;
        layout.setConstraints(label, s_layout);
        
        s_layout.insets = new Insets(10,0,0,0);
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
        
        s_layout.insets = new Insets(20,10,10,0);
        s_layout.gridwidth=0;

        layout.setConstraints(Analysis, s_layout);
        
        s_layout.anchor= GridBagConstraints.WEST ;

        s_layout.insets = new Insets(5,30,5,0);
        layout.setConstraints(info_3, s_layout);
        layout.setConstraints(info_2, s_layout);
        s_layout.insets = new Insets(5,20,25,0);
        layout.setConstraints(info_1, s_layout);

        
        MainPanel.add(menubar,BorderLayout.NORTH);
        //MainPanel.add(label,BorderLayout.CENTER);
        MainPanel.add(PathChoose,BorderLayout.SOUTH);
        ((JPanel)MainPanel).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。 
        ((JPanel)PathChoose).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。 



        this.setSize(658,530);
        this.setVisible(true);//设为可见
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
	
	public  void saveobject(String a,String b,String c,String d,String e,String f,String g,int i,Object[][] table){

		table[i][0] = a;
		table[i][1] =b;
		table[i][2] =c;
		table[i][3] =d;
	    table[i][4] =e;
	    table[i][5] =f;
	    table[i][6] =g;
	}
	public static void setday(int monnum,int yearnum,JComboBox<String> a)
	{
		a.removeAllItems();
		if(monnum== 1 || monnum ==3 || monnum ==5|| monnum ==7|| monnum ==8|| monnum ==10|| monnum ==12)
    	{
    		for(int i = 1;i < 32;i ++)
    		    a.addItem(String.valueOf(i));
    	}
    	else if(monnum == 2)
    	{
    		if(leap(yearnum))
    			for(int i = 1;i < 30;i ++)
    				a.addItem(String.valueOf(i));
    		else
    			for(int i = 1;i < 29;i ++)
        		    a.addItem(String.valueOf(i));
    	}
    	else if(monnum == 4 || monnum ==6 || monnum ==9|| monnum ==11)
    	{
    		for(int i = 1;i < 31;i ++)
    		    a.addItem(String.valueOf(i));
    	}
	}
	public static boolean leap(int i)
	{
		if(i%4==0 && i%100!=0 || i%400==0)
		    return true;
		else
			return false;
	}
	public Date change(JComboBox y,JComboBox m,JComboBox d,JComboBox h,JComboBox mi,JComboBox s)
	{

    	DateFormat df = new SimpleDateFormat("yyyy,MM,dd,hh,mm,ss");
    	StringBuffer date = new StringBuffer();

    	date.append((String) y.getSelectedItem());
    	date.append(",");
    	date.append((String) m.getSelectedItem());
    	date.append(",");
    	date.append((String) d.getSelectedItem());
    	date.append(",");
    	date.append((String) h.getSelectedItem());
    	date.append(",");
    	date.append((String) mi.getSelectedItem());
    	date.append(",");
    	date.append((String) s.getSelectedItem());
		Date a = null;
		try {
			a = df.parse(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;

	}
	private class FollowIPActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	if(globleStatus.getresult){
        	String inputValue = JOptionPane.showInputDialog("请输入所要查询的IP地址:(格式xxx.xxx.xxx.xxx)");
        	String regex = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
        			+ "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\"
        			+ "d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

        	ipstring = inputValue;
        	ipnum = 0;

            ResultVector resultVector = ResultVector.getInstance();
            resultVector.Clear();
            LogDataItem v1;
        	if(inputValue.matches(regex))
        	{
        		for(int i = 0;i <vector.size();i ++)
        		{
        			v1=(LogDataItem) vector.m_element.get(i);
        			if(v1.client_ip.equals(inputValue))
        			{
        				ipnum ++;
        				System.out.println(ipnum);
        			}
        		}
    			Object[][] temptable = new Object[ipnum][7];
            	if(ipnum > 0)
            	{
        			int i_all = 0;
        			for (int j = 0;j <vector.size(); j++)
        			{
        				    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        					LogDataItem v2=(LogDataItem) vector.m_element.get(j);
        					if(v2.client_ip.equals(inputValue))
        					{
        						 saveobject(df.format(v2.date),v2.client_ip,v2.request_method,
        								 v2.url_stem,v2.url_query,v2.status,v2.User_Agent,i_all,temptable);
        						 resultVector.addElement(v2);
        				          i_all ++;
        					}
        				}
        			SearchOutput output = new SearchOutput();
        			output.setTable(temptable);
            	}
            	else{
            		JOptionPane.showMessageDialog(null, "未找到符合的结果!", "错误信息",
                            JOptionPane.ERROR_MESSAGE);
            	}
            }
        	else{
        		JOptionPane.showMessageDialog(null, "IP地址格式错误!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
        	}
        	else{
            	JOptionPane.showMessageDialog(null, "不存在检测结果!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
        } 
    }
	private class FollowTimeActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
          	JPanel con = new JPanel();
        	boolean fromLeap  = true;
        	boolean toLeap  = true;
        	
        	JButton ensure = new JButton("确定");
        	ensure.addActionListener(new ensureActionListener());
        	JLabel from = new JLabel("起始时间");
        	JLabel to = new JLabel("结束时间");
        	JLabel ye = new JLabel("年");
        	JLabel mo = new JLabel("月");
        	JLabel da = new JLabel("日");
        	JLabel ho = new JLabel("时");
        	JLabel mi = new JLabel("分");
        	JLabel se = new JLabel("秒");
        	
        	JLabel ye2 = new JLabel("年");
        	JLabel mo2 = new JLabel("月");
        	JLabel da2 = new JLabel("日");
        	JLabel ho2 = new JLabel("时");
        	JLabel mi2 = new JLabel("分");
        	JLabel se2 = new JLabel("秒");
        	

        	SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
    		    String a = df.format(new Date());
    		    final int y = Integer.parseInt(a);
    		    
    	    	SimpleDateFormat df2 = new SimpleDateFormat("MM");//设置日期格式
    		    String b = df2.format(new Date());
    		    int m = Integer.parseInt(b);
    		    
    	    	SimpleDateFormat df3 = new SimpleDateFormat("dd");//设置日期格式
    		    String c = df3.format(new Date());
    		    int d = Integer.parseInt(b);
    		    System.out.println(d);

        	for(int i = 0;i < 10;i ++ )//
        	{
        		yearList.addItem(String.valueOf(y-i));
        	}
        	yearList2.addItem(String.valueOf(y));
        	for(int i = 1;i < 13;i ++ )//Index = 0时月份为1
        	{
        		monthList.addItem(String.valueOf(i));
        		monthList2.addItem(String.valueOf(i));
        	}
        	monthList.setSelectedIndex(m-1);
        	monthList2.setSelectedIndex(m-1);

        	setday(m, y, dayList);
        	setday(m, y, dayList2);

        	for(int i = 0;i < 24;i ++ )
        	{
        		hourList.addItem(String.valueOf(i));
        		hourList2.addItem(String.valueOf(i));
        	}

        	for(int i = 0;i < 60;i ++ )
        	{
        		minuteList.addItem(String.valueOf(i));
        		minuteList2.addItem(String.valueOf(i));
        	}

        	for(int i = 0;i < 60;i ++ )
        	{
        		secondList.addItem(String.valueOf(i));
        		secondList2.addItem(String.valueOf(i));
        	}
        	
        	yearList.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e) { 
                	int yea = yearList.getSelectedIndex();//0-2016,1-2015
                	int mon = monthList.getSelectedIndex();
                	if( mon == 1)
                		setday(mon + 1, yea, dayList);
                	yearList2.removeAllItems();
                	for(int i = 0;i <= yea;i ++)
                		yearList2.addItem(String.valueOf(y-i));
                }  
            });
        	

        	monthList.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e) {  
                	int mon = monthList.getSelectedIndex();
                	String temp = (String) yearList.getSelectedItem();
                	int yea = Integer.parseInt(temp);
                	mon ++;
                	setday(mon, yea,dayList);
                	if(yearList.getSelectedItem().equals(yearList2.getSelectedItem()))
                	{
                		monthList2.removeAllItems();
                		for(int i = mon;i <= 12;i ++)//mon是月份，4月
                			monthList2.addItem(String.valueOf(i));
                		setday(mon, yea,dayList2);
                	}
                }  
            });
        	
        	yearList2.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e) { 
                	int yea2 = yearList2.getSelectedIndex();
                	String temp = (String) monthList.getSelectedItem();
                	int mon2 = Integer.parseInt(temp);
                	if(mon2 == 1)
                		setday(mon2 + 1, yea2, dayList2);
                }
            });
        	
        	monthList2.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e) {  
                	int mon2 = monthList2.getSelectedIndex();
                	String temp = (String) yearList2.getSelectedItem();
                	int yea2 = Integer.parseInt(temp);
                	mon2 ++;
                	setday(mon2, yea2,dayList2);
                }  
            });
        	
        	GridBagLayout layout = new GridBagLayout();
            con.setLayout(layout);con.add(from);
            con.add(yearList);con.add(ye);con.add(monthList);con.add(mo); 
            con.add(dayList);con.add(da);con.add(hourList);con.add(ho); 
            con.add(minuteList); con.add(mi);con.add(secondList);con.add(se);
            
            con.add(to);

            con.add(yearList2);con.add(ye2);con.add(monthList2);con.add(mo2);
            con.add(dayList2);con.add(da2);con.add(hourList2);con.add(ho2);
            con.add(minuteList2);con.add(mi2); con.add(secondList2);con.add(se2);
            con.add(ensure);
            
            GridBagConstraints s_layout= new GridBagConstraints();
            
            s_layout.fill = GridBagConstraints.CENTER;
            s_layout.insets = new Insets(10,2,10,2);//top, left,  bottom, right
            s_layout.gridwidth=1;
            layout.setConstraints(ye, s_layout);
            layout.setConstraints(mo, s_layout);
            layout.setConstraints(da, s_layout);
            layout.setConstraints(ho, s_layout);
            layout.setConstraints(mi, s_layout);
            
            layout.setConstraints(ye2, s_layout);
            layout.setConstraints(mo2, s_layout);
            layout.setConstraints(da2, s_layout);
            layout.setConstraints(ho2, s_layout);
            layout.setConstraints(mi2, s_layout);

            
            s_layout.insets = new Insets(10,0,10,0);//top, left,  bottom, right
            s_layout.gridwidth=1;
            layout.setConstraints(yearList, s_layout);
            layout.setConstraints(monthList, s_layout);
            layout.setConstraints(dayList, s_layout);
            layout.setConstraints(hourList, s_layout);
            layout.setConstraints(minuteList, s_layout);
            layout.setConstraints(secondList, s_layout);
           
           
            layout.setConstraints(yearList2, s_layout);
            s_layout.insets = new Insets(10,0,10,0);
            layout.setConstraints(monthList2, s_layout);
            layout.setConstraints(dayList2, s_layout);
            layout.setConstraints(hourList2, s_layout);
            layout.setConstraints(minuteList2, s_layout);
            
            layout.setConstraints(secondList2, s_layout);
            s_layout.insets = new Insets(10,0,10,10);
            layout.setConstraints(to, s_layout);
            layout.setConstraints(from, s_layout);
            s_layout.gridwidth=0;
            s_layout.insets = new Insets(10,0,10,0);
            layout.setConstraints(se2, s_layout); 
            layout.setConstraints(ensure, s_layout);
            layout.setConstraints(se, s_layout);
            
        	time.add(con);
        	time.setSize(500, 200);
        	time.setVisible(true);
        } 
    }
	
	private class ensureActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	Demo.fromtime = change(yearList,monthList,dayList,hourList,minuteList,secondList);
        	Demo.totime =change(yearList2,monthList2,dayList2,hourList2,minuteList2,secondList2);
        	if(Demo.fromtime.getTime() > Demo.totime.getTime())
        	{
        		JOptionPane.showMessageDialog(null, "起始时间晚于结束时间!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
        	else
        	{
        		time.setVisible(false);
        		dothat();
        	}
        } 
    }
	
	private class AboutActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	JDialog aboutDialog = new JDialog();
    		ImageIcon bg = new ImageIcon("picture//aboutbg.jpeg");
    		JLabel imgLabel = new JLabel(bg);
    		aboutDialog.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
            imgLabel.setBounds(0,0,bg.getIconWidth(), bg.getIconHeight());//设置背景标签的位置
            Container AboutPanel=aboutDialog.getContentPane();  
    		JLabel aJLabel= new JLabel("版本：v1.0");
    		JLabel bJLabel= new JLabel("联系方式:onlyerir@163.com");

            GridBagLayout layout = new GridBagLayout();
            AboutPanel.setLayout(layout);
            AboutPanel.add(aJLabel);
            AboutPanel.add(bJLabel);

            GridBagConstraints s_layout= new GridBagConstraints();
            s_layout.anchor= GridBagConstraints.WEST ;
            s_layout.insets = new Insets(230,0,0,300);//top, left,  bottom, right
            s_layout.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
            layout.setConstraints(aJLabel, s_layout);
            s_layout.insets = new Insets(10,0,0,300);//top, left,  bottom, right
            layout.setConstraints(bJLabel, s_layout);
            
            ((JPanel)AboutPanel).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。 

    		aboutDialog.setSize(500,400);
    		aboutDialog.setVisible(true);;        } 
    }
	
	public  void dothat()
	{
		if(globleStatus.getresult){
        	
        	timenum = 0;
            ResultVector resultVector = ResultVector.getInstance();
            resultVector.Clear();
            LogDataItem v1;
        		for(int i = 0;i <vector.size();i ++)
        		{
        			v1=(LogDataItem) vector.m_element.get(i);
        			if (fromtime.getTime() <= v1.date.getTime()  && v1.date.getTime()<= totime.getTime()) 
        			{
        				timenum ++;
        				System.out.println(timenum);
        			}
        		}
    			Object[][] temptable = new Object[timenum][7];
            	if(timenum > 0)
            	{
        			int i_all = 0;
        			for (int j = 0;j <vector.size(); j++)
        			{
        				    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        					LogDataItem v2=(LogDataItem) vector.m_element.get(j);
        					if (fromtime.getTime() <= v2.date.getTime()  && v2.date.getTime()<= totime.getTime()) 
        					{
        						 saveobject(df.format(v2.date),v2.client_ip,v2.request_method,
        								 v2.url_stem,v2.url_query,v2.status,v2.User_Agent,i_all,temptable);
        						 resultVector.addElement(v2);
        				          i_all ++;
        					}
        				}
        			SearchOutput output = new SearchOutput();
        			output.setTable(temptable);
            	}
            	else{
            		JOptionPane.showMessageDialog(null, "未找到符合的结果!", "错误信息",
                            JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	else{
            	JOptionPane.showMessageDialog(null, "不存在检测结果!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
	}
	private class FollowKeyActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	if(globleStatus.getresult){
        	String inputValue = JOptionPane.showInputDialog("请输入所要查询的关键字:");
        	StringBuffer add = new StringBuffer();
        	add.append("(.*)");
        	add.append(inputValue);
        	add.append("(.*)");
        	keystring = add.toString();
        	keynum = 0;
            ResultVector resultVector = ResultVector.getInstance();
            resultVector.Clear();
            LogDataItem v1;
        		for(int i = 0;i <vector.size();i ++)
        		{
        			v1=(LogDataItem) vector.m_element.get(i);
        			if(v1.url_stem.equals(keystring) || v1.url_query.equals(keystring))
        			{
        				keynum ++;
        				System.out.println(keynum);
        			}
        		}
    			Object[][] temptable = new Object[keynum][7];
            	if(keynum > 0)
            	{
        			int i_all = 0;
        			for (int j = 0;j <vector.size(); j++)
        			{
        				    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        					LogDataItem v2=(LogDataItem) vector.m_element.get(j);
        					if(v2.url_stem.equals(keystring) || v2.url_query.equals(keystring))
        					{
        						 saveobject(df.format(v2.date),v2.client_ip,v2.request_method,
        								 v2.url_stem,v2.url_query,v2.status,v2.User_Agent,i_all,temptable);
        						 resultVector.addElement(v2);
        				          i_all ++;
        					}
        				}
        			SearchOutput output = new SearchOutput();
        			output.setTable(temptable);
            	}
            	else{
            		JOptionPane.showMessageDialog(null, "未找到符合的结果!", "错误信息",
                            JOptionPane.ERROR_MESSAGE);
            	}
        	}
        	else{
            	JOptionPane.showMessageDialog(null, "不存在检测结果!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
        } 
    }
	
	private class AllResultActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	if(globleStatus.getresult)
        	{
        		resultoutput show = new  resultoutput();
        		show.resultoutputui();
        	}
        	else{
            	JOptionPane.showMessageDialog(null, "不存在检测结果!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
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
        	MyFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
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
               
               globleStatus.multi = false;
               globleStatus.getresult = false;
               info_3.setText("     ");
            }
        } 
    }
	private class CreatemultiActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("你按了按钮");   
        	MyFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        	MyFile.setMultiSelectionEnabled(true);
            MyFile.showOpenDialog(null);  //弹出文件选择
            File[] f = MyFile.getSelectedFiles();  //得到文件

            for(int i = 0;i < f.length;i ++){
            if(f != null ){
            	System.out.println(f[i].getAbsolutePath());   
                globleStatus.filenames.add(f[i].getAbsolutePath());//把日志的绝对路径给全局状态
               text.setText(text.getText()+";"+globleStatus.filenames.get(i));
               //清空以往数据
               MiddleDataVector vector_clear = MiddleDataVector.getInstance();
               vector_clear.Clear();
           	   judgement.num_sql = 0 ;
        	    judgement.num_xss = 0 ;
        	    judgement.num_exec = 0 ;
        	    statistics.Clear();
               globleStatus.multi = true;
               globleStatus.getresult = false;
               info_3.setText("     ");
            }
        }

        }
    }
	
	private class SaveActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  

        	int value = 0;
        	MyFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
        	File f=null;
        	 
        	 try{     
        		 value = MyFile.showOpenDialog(null);  //弹出文件选择
        		 }    
             catch(HeadlessException head){     
                  System.out.println("Open File Dialog ERROR!");    
             }
 			if(value==JFileChooser.APPROVE_OPTION){
                 f=MyFile.getSelectedFile();    
                 ResultVector resultVector = ResultVector.getInstance();
                 resultVector.Clear();
                 sqlattacklist sl = sqlattacklist.getInstance();
                 xssattacklist xl = xssattacklist.getInstance();
                 execattacklist el = execattacklist.getInstance();
                 LogDataItem v;
                 for(int i = 0;i < sl.size();i ++)
                 {
                	 v=(LogDataItem) sl.m_sqlelement.get(i);
                	 resultVector.addElement(v);
                 }
                 for(int i = 0;i < xl.size();i ++)
                 {
                	 v=(LogDataItem) xl.m_xsselement.get(i);
                	 resultVector.addElement(v);
                 }
                 for(int i = 0;i < el.size();i ++)
                 {
                	 v=(LogDataItem) el.m_execelement.get(i);
                	 resultVector.addElement(v);
                 }
                 ResultSave output = new ResultSave(f.getPath(),resultVector);
 			}
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
            			info_3.setText("分析已完成!");
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
             			info_3.setText("分析已完成!");
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
    



