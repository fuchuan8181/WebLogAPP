package UI;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import Logic.statistics;

public class table {
	JDialog jf = new JDialog();  
	JTable url_table;
	public table() {
	// TODO Auto-generated constructor stub

		Integer sql_num;
		
		TableColumn column = null;
		Map<String, Integer> sqli = statistics.sql_url;
		Map<String, Integer> xss = statistics.xss_url;	
		Map<String, Integer> exec = statistics.exec_url;
		sql_num = sqli.size();
		Object[][] tableData = new Object[sql_num][2];  
		int i = 0;
		for (Map.Entry<String, Integer> entry : sqli.entrySet()) {  
			tableData[i][1] = entry.getValue();  
            tableData[i][0] = entry.getKey();  
            i ++;
		}
                                                                                                                                                    
		String[] columnTitle = {"¹¥»÷URLµØÖ·"  , "¹¥»÷´ÎÊý"};

		url_table= new JTable(tableData,columnTitle);
		url_table.setRowHeight(30);
		url_table.setPreferredScrollableViewportSize(new Dimension(500, 309));  
		//url_table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  
		
		//for (int i = 0; i < 2; i++) {  
            column = url_table.getColumnModel().getColumn(0);  
           // if ((i % 2) == 0)  
                column.setPreferredWidth(80);  
           // else  
                column = url_table.getColumnModel().getColumn(1);  
                column.setPreferredWidth(1);  
        //}  
		//url_table.setShowVerticalLines(true);
		//url_table= new JTable(d,columnTitle);
		

		JScrollPane jScrollPane;
		//jf.add(url_table);
		jf.add( jScrollPane = new JScrollPane(url_table));  
		//jScrollPane1.setPreferredSize(new Dimension(500, 309));
        jf.pack();  
        jf.setVisible(true);  
	}
		}
	 
	
