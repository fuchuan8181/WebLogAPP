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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


import Chart.barChart;
import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Logic.judgement;


public class resultoutput {
	JDialog result = new JDialog();
	JLabel title =  new JLabel("日志分析报告");

	JTable content;
	JButton showcitychart;//获取文件路径
	JButton showattacktable;//分析文件
	
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

		int i_sql = 0;
		int i_xss = 0;
		int i_exec = 0;
		int i_all = 0;
		
		 TableColumn column = null;  
		title.setFont(new Font("黑体",Font.BOLD,32));
		
		Object[][] sqlData = new Object[ judgement.num_sql][8];
		Object[][] xssData = new Object[judgement.num_xss][8];
		Object[][] execData = new Object[judgement.num_exec][8];
		
		Object[][] tableData = new Object[ judgement.num_sql + judgement.num_xss+judgement.num_exec][8];

		for (int j = 0;j <vector.size(); j++)
		{
				LogDataItem v=(LogDataItem) vector.m_element.get(j);
				if(v.bSQL == true)
				{
					 saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"sql注入攻击",v.status,v.User_Agent,i_sql,sqlData);
					 saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"sql注入攻击",v.status,v.User_Agent,i_all,tableData);
			          i_sql ++;
			          i_all ++;
				}
				else if(v.bXSS == true)
				{
					saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"XSS跨站攻击",v.status,v.User_Agent,i_xss,xssData);
				    saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"XSS跨站攻击",v.status,v.User_Agent,i_all,tableData);
			         i_xss ++;
			         i_all ++;
				}
				else if(v.bEXEC == true)
				{
					saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"可执行命令攻击",v.status,v.User_Agent,i_exec,execData);
				    saveobject(v.date,v.server_ip,v.request_method,v.url_stem,v.url_query,"可执行命令攻击",v.status,v.User_Agent,i_all,tableData);
			        i_exec ++;
			        i_all ++;
				}
	}
			String[] columnTitle = {"访问时间","访问IP","请求方法","访问URL地址","提交参数","攻击方法","状态码","浏览器信息"};
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
		
		showattacktable= new JButton("显示各URL遭受攻击次数");
        showattacktable.addActionListener(new attackActionListener());
        
        
        showcitychart= new JButton("显示各城市遭受攻击次数");
        showcitychart.addActionListener(new cityActionListener());
		
		JPanel tit = new JPanel();
		JPanel con = new JPanel();
		JPanel but = new JPanel();
		
		JScrollPane jScrollPane;
		
		tit.add(title,BorderLayout.CENTER);
		con.add(jScrollPane = new JScrollPane(content));
		but.add(showcitychart,BorderLayout.WEST);
		but.add(showattacktable, BorderLayout.EAST);
		but.setBorder(BorderFactory.createEmptyBorder(0,20,30,20));
		
		result.setSize(1500, 800);
		result.add(tit,BorderLayout.NORTH);
		result.add(con,BorderLayout.CENTER);
		result.add(but,BorderLayout.SOUTH);
		result.setVisible(true);
		
	}

	private class attackActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("你按了攻击类型分析");   
            new table();
            }
    }
	private class cityActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("你按了城市攻击分析");   
           barChart chart = new barChart();
    		chart.getBarChart();
            }
    }
}
