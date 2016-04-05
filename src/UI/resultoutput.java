package UI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import Chart.barChart;
import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.execattacklist;
import Gloable.sqlattacklist;
import Gloable.xssattacklist;
import Logic.judgement;


public class resultoutput {
	JDialog result = new JDialog();
	JLabel title =  new JLabel("��־��������");

	JTable content;
	JButton showcitychart;//��ȡ�ļ�·��
	JButton showattacktable;//�����ļ�
	
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
      //CreateOneMenu.addActionListener(new CreateOneActionListener());
      JMenuItem CloseMenu= new JMenuItem("�ر�");
      //CreateMultiMenu.addActionListener(new CreateMultiActionListener());
      JMenuItem Acord2TimeMenu= new JMenuItem("��ʱ��β���");
      JMenuItem Acord2IPAddMenu= new JMenuItem("��IP��ַ����");
      //JMenuItem ACord2AttackMenu= new JMenuItem("��������Ϊ����");
      JMenuItem ACord2KeyMenu= new JMenuItem("���ؼ��ֲ���");
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
		
		showattacktable= new JButton("��ʾ��URL���ܹ�������");
        showattacktable.addActionListener(new attackActionListener());
        
        
        showcitychart= new JButton("��ʾ���������ܹ�������");
        showcitychart.addActionListener(new cityActionListener());
		
		JPanel tit = new JPanel();
		JPanel con = new JPanel();
		JPanel but = new JPanel();
		
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
            //System.out.println("�㰴�˹������ͷ���");   
            new table();
            }
    }
	private class cityActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            //System.out.println("�㰴�˳��й�������");   
           barChart chart = new barChart();
    		chart.getBarChart();
            }
    }
}
