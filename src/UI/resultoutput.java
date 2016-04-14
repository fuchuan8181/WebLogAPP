package UI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import Chart.barChart;
import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.ResultVector;
import Gloable.execattacklist;
import Gloable.sqlattacklist;
import Gloable.xssattacklist;
import Logic.ResultSave;
import Logic.judgement;



public class resultoutput extends JDialog{
	//JDialog result = new JDialog();
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


	JPanel con = new JPanel();

	JScrollPane jScrollPane;
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();

	static Date fromtime;
	static Date totime;


	//在这里把所有攻击结果分开保存在各个list中
	
	static Object[][] tableData = new Object[ judgement.num_sql + judgement.num_xss+judgement.num_exec][8];
	
	String[] columnTitle = {"访问时间","访问IP","请求方法","访问URL地址","提交参数","攻击方法","状态码","浏览器信息"};
	String ipstring = null;
	String keystring = null;
	int ipnum = 0;
	int timenum = 0;
	int keynum = 0;
	public  void saveobject(String a,String b,String c,String d,String e,String f,String g,String h,int i,Object[][] table){

		table[i][0] = a;
		table[i][1] = b;
		table[i][2] =c;
		table[i][3] =d;
	    table[i][4] =e;
	    table[i][5] =f;
	    table[i][6] =g;
	    table[i][7] =h;
	}
    public JTable settable(JTable a)
    {
		    TableColumn column = null;  
   		    
			column = a.getColumnModel().getColumn(0);
			column.setPreferredWidth(130);
			column = a.getColumnModel().getColumn(1);
			column.setPreferredWidth(110);
			column = a.getColumnModel().getColumn(2);
			column.setPreferredWidth(50);
			column = a.getColumnModel().getColumn(3);
			column.setPreferredWidth(180);
			column = a.getColumnModel().getColumn(4);
			column.setPreferredWidth(555);
			column = a.getColumnModel().getColumn(6);
			column.setPreferredWidth(40);
			column = a.getColumnModel().getColumn(7);
			column.setPreferredWidth(260);
			
			DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			a.setDefaultRenderer(Object.class,   r);
    	return a;
    }
	
	public void resultoutputui()
	{
		JTable content;
		MiddleDataVector vector=MiddleDataVector.getInstance();
		JMenuBar menubar = new JMenuBar();

	   	 JMenu FileMenu = new JMenu("文件");
	   	 JMenu SearchMenu = new JMenu("查找");
	   	 JMenu StatisticMenu = new JMenu("统计");
	   	 
	   	JMenuItem SaveMenu= new JMenuItem("保存");
      SaveMenu.addActionListener(new SaveActionListener());
      JMenuItem CloseMenu= new JMenuItem("关闭");
      CloseMenu.addActionListener(new CloseActionListener());
      JMenuItem Acord2TimeMenu= new JMenuItem("按时间段查找");
      Acord2TimeMenu.addActionListener(new Acord2TimeActionListener());
      JMenuItem Acord2IPAddMenu= new JMenuItem("按IP地址查找");
      Acord2IPAddMenu.addActionListener(new Acord2IPAddActionListener());
      JMenuItem ACord2KeyMenu= new JMenuItem("按关键字查找");
      ACord2KeyMenu.addActionListener(new Acord2KeyActionListener());
      JMenuItem StatisAddMenu= new JMenuItem("地域统计");
      StatisAddMenu.addActionListener(new cityActionListener());
      JMenuItem StaticURLMenu= new JMenuItem("URL统计");
      StaticURLMenu.addActionListener(new attackActionListener());
      
      FileMenu.add(SaveMenu);
      FileMenu.add(CloseMenu);
      SearchMenu.add(Acord2TimeMenu);
      SearchMenu.add(Acord2IPAddMenu);
      SearchMenu.add(ACord2KeyMenu);
      StatisticMenu.add(StatisAddMenu);
      StatisticMenu.add(StaticURLMenu);
      
      menubar.add(FileMenu);
      menubar.add(SearchMenu);
      menubar.add(StatisticMenu);

		int i_all = 0;
		

		// System.out.println(judgement.num_sql + judgement.num_xss+judgement.num_exec);
		for (int j = 0;j <vector.size(); j++)
		{
			    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				LogDataItem v=(LogDataItem) vector.m_element.get(j);
				if(v.bSQL == true)
				{
					 saveobject(df.format(v.date),v.client_ip,v.request_method,v.url_stem,v.url_query,"sql注入攻击",v.status,v.User_Agent,i_all,tableData);
			          i_all ++;
				}
				else if(v.bXSS == true)
				{
				    saveobject(df.format(v.date),v.client_ip,v.request_method,v.url_stem,v.url_query,"XSS跨站攻击",v.status,v.User_Agent,i_all,tableData);

			         i_all ++;
				}
				else if(v.bEXEC == true)
				{
				    saveobject(df.format(v.date),v.client_ip,v.request_method,v.url_stem,v.url_query,"可执行命令攻击",v.status,v.User_Agent,i_all,tableData);
			        i_all ++;
				}
			}

		content= new JTable(tableData,columnTitle);
		content.setPreferredScrollableViewportSize(new Dimension(1400, 600));
		content.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
		
		content = settable(content);     

		con.add(jScrollPane = new JScrollPane(content));

		con.setBorder(BorderFactory.createEmptyBorder(0,20,30,20));
		
		this.setSize(1500, 800);
		this.add(menubar,BorderLayout.NORTH);
		
		this.add(con,BorderLayout.CENTER);
		//result.add(but,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	private class SaveActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //保存总攻击结果

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
	private class CloseActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //关闭对话框
        	disappear(e);
            }
    }
	
    private void disappear(ActionEvent evt) {  //将对话框设置为不可见
        this.setVisible(false);
    }  
	
	private class cityActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //显示地域统计结果
           barChart chart = new barChart();
    		chart.getBarChart();
            }
    }
	private class attackActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //攻击列表统计
            new table();
            }
    }
	

    
    private void nextPanel(int type) {  //分流显示
        this.remove(con);  
        switch (type) {
		case 0://时间查找结果
	        con = new StepIPPanel();
			break;
		case 1:
			con = new StepTimePanel();
			break;
		case 2:
			con = new StepKeyPanel();
			break;
		default:
			break;
		}
        this.add(con, BorderLayout.CENTER);  
        this.validate(); 
        this.repaint();
    }  

	
	private class Acord2TimeActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //按时间查找结果
        	//TimeSelect TS = new TimeSelect();
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
	public  void dothat()
	{
    	Date temptime = null;
    	timenum = 0;
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(int i = 0;i < judgement.num_sql + judgement.num_xss+judgement.num_exec;i ++)
		{
			try {
				//System.out.println(tableData[i][0]);
				temptime = df.parse((String) tableData[i][0]);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (resultoutput.fromtime.getTime() <= temptime.getTime()  && temptime.getTime()<  resultoutput.totime.getTime()) 
			{
				timenum ++;
			}
        }
		if(timenum > 0)
		{
    		nextPanel(1);  
		}
    	else{
    		JOptionPane.showMessageDialog(null, "未找到符合的结果!", "错误信息",
                    JOptionPane.ERROR_MESSAGE);
    	}

	}
	
    private class StepTimePanel extends JPanel {  //IP查找结果显示
        public StepTimePanel() {  
        	int k = 0;
        	Date temptime = null;
    		Object[][] tableDatatime = new Object[timenum][8];
    	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		for(int i = 0;i < judgement.num_sql + judgement.num_xss+judgement.num_exec;i ++)
    		{
    			try {
    				System.out.println((String) tableData[i][0]);
					temptime = df.parse((String) tableData[i][0]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			if (fromtime.getTime() <= temptime.getTime()  && temptime.getTime()<= totime.getTime()) 
    			{
    				System.out.println("存在该时间段");
    				for(int j = 0; j < 8;j ++)
    					tableDatatime[k][j] =tableData[i][j];
    				k ++;
    			}
    		}

    		JTable content2;
    		content2= new JTable(tableDatatime,columnTitle);
    		
    		content2.setPreferredScrollableViewportSize(new Dimension(1400, 600));
    		content2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
    		content2 = settable(content2);     
    		
    		JPanel label2 = new JPanel();
    		JScrollPane jScrollPane2;
    		label2.add(jScrollPane2 = new JScrollPane(content2));
            this.add(label2, BorderLayout.CENTER);
        }
        }
	
	private class Acord2IPAddActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //按IP查找结果
        	String inputValue = JOptionPane.showInputDialog("请输入所要查询的IP地址:(格式xxx.xxx.xxx.xxx)");
        	String regex = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
        			+ "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\"
        			+ "d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

        	ipstring = inputValue;
        	ipnum = 0;
        	if(inputValue.matches(regex))
        	{
        		for(int i = 0;i < judgement.num_sql + judgement.num_xss+judgement.num_exec;i ++)
        			if(tableData[i][1].equals(inputValue))
        				ipnum ++;
            	if(ipnum > 0)
            		nextPanel(0);  
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
    }
	
    private class StepIPPanel extends JPanel {  //IP查找结果显示
        public StepIPPanel() {  
            //this.setLayout(new BorderLayout());  
            	int k = 0;
            		Object[][] tableDataip = new Object[ipnum][8];
            		for(int i = 0;i < judgement.num_sql + judgement.num_xss+judgement.num_exec;i ++)
            		{
            			if(tableData[i][1].equals(ipstring))
            			{
            				for(int j = 0; j < 8;j ++)
            					tableDataip[k][j] =tableData[i][j];
            				k ++;
            			}
            		}
            		
            		JTable content1;
            		content1= new JTable(tableDataip,columnTitle);
            		
            		content1.setPreferredScrollableViewportSize(new Dimension(1400, 600));
            		content1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
            		content1 = settable(content1);            		
            		
            		JPanel label = new JPanel();
            		JScrollPane jScrollPane1;
            		label.add(jScrollPane1 = new JScrollPane(content1));
                    this.add(label, BorderLayout.CENTER);
        	}

    }  
    

	
	private class Acord2KeyActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  //按关键字查找结果
        	String inputValue = JOptionPane.showInputDialog("请输入所要查询的关键字:");
        	StringBuffer add = new StringBuffer();
        	add.append("(.*)");
        	add.append(inputValue);
        	add.append("(.*)");
        	keystring = add.toString();
        	keynum = 0;
    		for(int i = 0;i < judgement.num_sql + judgement.num_xss+judgement.num_exec;i ++)
    			if(((String) tableData[i][3]).matches(keystring) || ((String) tableData[i][4]).matches(keystring))
    				keynum ++;
        	if(keynum > 0)
        		nextPanel(2);  
        	else{
        		JOptionPane.showMessageDialog(null, "未找到符合的结果!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
        	}
            }
    }
    private class StepKeyPanel extends JPanel {  //IP查找结果显示
        public StepKeyPanel() {  
            //this.setLayout(new BorderLayout());  
            	int k = 0;
            		Object[][] tableDatakey = new Object[keynum][8];
            		for(int i = 0;i < judgement.num_sql + judgement.num_xss+judgement.num_exec;i ++)
            		{
            			if(((String) tableData[i][3]).matches(keystring) || ((String) tableData[i][4]).matches(keystring))
            			{
            				for(int j = 0; j < 8;j ++)
            					tableDatakey[k][j] =tableData[i][j];
            				k ++;
            			}
            		}
            		
            		JTable content3;
            		content3= new JTable(tableDatakey,columnTitle);
            		
            		content3.setPreferredScrollableViewportSize(new Dimension(1400, 600));
            		content3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
            		content3 = settable(content3);            		
            		
            		JPanel label = new JPanel();
            		JScrollPane jScrollPane1;
            		label.add(jScrollPane1 = new JScrollPane(content3));
                    this.add(label, BorderLayout.CENTER);
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
	
	private class ensureActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	fromtime = change(yearList,monthList,dayList,hourList,minuteList,secondList);
        	totime =change(yearList2,monthList2,dayList2,hourList2,minuteList2,secondList2);
        	if(fromtime.getTime() > totime.getTime())
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

}
