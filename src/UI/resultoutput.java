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


public class resultoutput {
	JDialog result = new JDialog();
	JLabel title =  new JLabel("��־��������");

	JTable content;
	JButton showcitychart;//��ȡ�ļ�·��
	JButton showattacktable;//�����ļ�
	public resultoutput()
	{
		MiddleDataVector vector=MiddleDataVector.getInstance();
		Integer num_temp = 0 ;//= vector.size();
		for (int j = 0;j <vector.size(); j++)
		{
			LogDataItem v=(LogDataItem) vector.m_element.get(j);
			if(v.bSQL == true)
			{
				num_temp ++;
			}
		}
		Integer num = num_temp;
		int i = 0;
		 TableColumn column = null;  
		title.setFont(new Font("����",Font.BOLD,32));
		Object[][] tableData = new Object[num][8];

		for (int j = 0;j <vector.size(); j++)
		{
				LogDataItem v=(LogDataItem) vector.m_element.get(j);
				if(v.bSQL == true)
				{
				tableData[i][0] = v.date;
				tableData[i][1] =v.server_ip;
				tableData[i][2] =v.request_method;
				tableData[i][3] =v.url_stem;
			    tableData[i][4] = v.url_query;
			    tableData[i][5] ="sqlע�빥��";
			    tableData[i][6] =v.status;
			    tableData[i][7] =v.User_Agent;
			    i ++;
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
            System.out.println("�㰴�˹������ͷ���");   
            new table();
            }
    }
	private class cityActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("�㰴�˳��й�������");   
           barChart chart = new barChart();
    		chart.getBarChart();
            }
    }
}
