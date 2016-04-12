package UI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.sun.org.apache.bcel.internal.generic.NEW;

import Chart.barChart;
import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.execattacklist;
import Gloable.sqlattacklist;
import Gloable.xssattacklist;
import Logic.ResultSave;
import Logic.judgement;



public class resultoutput {
	JDialog result = new JDialog();
	JLabel title =  new JLabel("��־��������");

	JTable content;
	JButton showcitychart;//��ȡ�ļ�·��
	JButton showattacktable;//�����ļ�
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
	public  void saveobject(String a,String b,String c,String d,String e,String f,String g,String h,int i,Object[][] table){

		table[i][0] = a;
		table[i][1] =b;
		table[i][2] =c;
		table[i][3] =d;
	    table[i][4] =e;
	    table[i][5] =f;
	    table[i][6] =g;
	    table[i][7] =h;
	}
	
	public resultoutput()
	{
		MiddleDataVector vector=MiddleDataVector.getInstance();
		JMenuBar menubar = new JMenuBar();

	   	 JMenu FileMenu = new JMenu("�ļ�");
	   	 JMenu SearchMenu = new JMenu("����");
	   	 JMenu StatisticMenu = new JMenu("ͳ��");
	   	 
	   	JMenuItem SaveMenu= new JMenuItem("����");
      SaveMenu.addActionListener(new SaveActionListener());
      JMenuItem CloseMenu= new JMenuItem("�ر�");
      CloseMenu.addActionListener(new CloseActionListener());
      JMenuItem Acord2TimeMenu= new JMenuItem("��ʱ��β���");
      Acord2TimeMenu.addActionListener(new Acord2TimeActionListener());
      JMenuItem Acord2IPAddMenu= new JMenuItem("��IP��ַ����");
      Acord2IPAddMenu.addActionListener(new Acord2IPAddActionListener());
      JMenuItem ACord2KeyMenu= new JMenuItem("���ؼ��ֲ���");
      ACord2KeyMenu.addActionListener(new Acord2KeyActionListener());
      JMenuItem StatisAddMenu= new JMenuItem("����ͳ��");
      StatisAddMenu.addActionListener(new cityActionListener());
      JMenuItem StaticURLMenu= new JMenuItem("URLͳ��");
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
		
		 TableColumn column = null;  
		title.setFont(new Font("����",Font.BOLD,32));
		
		sqlattacklist list_sql = sqlattacklist.getInstance();
		xssattacklist list_xss = xssattacklist.getInstance();
		execattacklist list_exec = execattacklist.getInstance();
		
		Object[][] tableData = new Object[ judgement.num_sql + judgement.num_xss+judgement.num_exec][8];

		for (int j = 0;j <vector.size(); j++)
		{
				LogDataItem v=(LogDataItem) vector.m_element.get(j);
				if(v.bSQL == true)
				{
					list_sql.addElement(v);
					 saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"sqlע�빥��",v.status,v.User_Agent,i_all,tableData);
			          i_all ++;
				}
				else if(v.bXSS == true)
				{
					list_xss.addElement(v);
				    saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"XSS��վ����",v.status,v.User_Agent,i_all,tableData);

			         i_all ++;
				}
				else if(v.bEXEC == true)
				{
					list_exec.addElement(v);
				    saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"��ִ�������",v.status,v.User_Agent,i_all,tableData);

			        i_all ++;
				}
	}
			String[] columnTitle = {"����ʱ��","����IP","���󷽷�","����URL��ַ","�ύ����","��������","״̬��","�������Ϣ"};
		content= new JTable(tableData,columnTitle);
		content.setPreferredScrollableViewportSize(new Dimension(1400, 600));
		content.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
		
		column = content.getColumnModel().getColumn(0);
		column.setPreferredWidth(130);
		column = content.getColumnModel().getColumn(1);
		column.setPreferredWidth(110);
		column = content.getColumnModel().getColumn(2);
		column.setPreferredWidth(50);
		column = content.getColumnModel().getColumn(3);
		column.setPreferredWidth(180);
		column = content.getColumnModel().getColumn(4);
		column.setPreferredWidth(555);
		column = content.getColumnModel().getColumn(6);
		column.setPreferredWidth(40);
		column = content.getColumnModel().getColumn(7);
		column.setPreferredWidth(260);
		
		DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		content.setDefaultRenderer(Object.class,   r);
		
		// showattacktable= new JButton("��ʾ��URL���ܹ�������");
        //showattacktable.addActionListener(new attackActionListener());
        
        
       // showcitychart= new JButton("��ʾ���������ܹ�������");
        //showcitychart.addActionListener(new cityActionListener());
		
		//JPanel tit = new JPanel();
		JPanel con = new JPanel();
		//JPanel but = new JPanel();
		
		JScrollPane jScrollPane;
		
	
		con.add(jScrollPane = new JScrollPane(content));

		con.setBorder(BorderFactory.createEmptyBorder(0,20,30,20));
		
		result.setSize(1500, 800);
		result.add(menubar,BorderLayout.NORTH);
		
		result.add(con,BorderLayout.CENTER);
		//result.add(but,BorderLayout.SOUTH);
		result.setVisible(true);
		
	}

	private class attackActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            new table();
            }
    }
	
	private class CloseActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	result.setVisible(false);
            }
    }
	private Object makeObj(final String item)  {
	     return new Object() { public String toString() { return item; } };
	   }
	
	private class Acord2TimeActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	TimeSelect TS = new TimeSelect();
            }
    }
	
	private class Acord2IPAddActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	String inputValue = JOptionPane.showInputDialog("��������Ҫ��ѯ��IP��ַ:(��ʽxxx.xxx.xxx.xxx)");
        	String regex = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
        	if(inputValue.matches(regex))
        	{
        		//����
        	}
            }
    }
	
	private class Acord2KeyActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	String inputValue = JOptionPane.showInputDialog("��������Ҫ��ѯ�Ĺؼ���:");
            }
    }
	
	private class SaveActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  


        	MyFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//ֻ��ѡ��Ŀ¼
        	File f=null;
        	 
        	 try{     
        		 MyFile.showOpenDialog(null);  //�����ļ�ѡ��
        		 }    
             catch(HeadlessException head){     
                  System.out.println("Open File Dialog ERROR!");    
             }        
                 f=MyFile.getSelectedFile();    
                 ResultSave output = new ResultSave(f.getPath());
            }
    }
	
	private class cityActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           barChart chart = new barChart();
    		chart.getBarChart();
            }
    }
}
