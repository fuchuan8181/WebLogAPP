package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import Gloable.ResultVector;
import Logic.ResultSave;


public class SearchOutput extends JDialog{
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();

	
	public void setTable(Object[][] ta)
	{
		JTable content;
		JPanel con = new JPanel();
		JScrollPane jScrollPane;
		String[] columnTitle = {"访问时间","访问IP","请求方法","访问URL地址","提交参数","状态码","浏览器信息"};
		JMenuBar menubar = new JMenuBar();

	   	 JMenu FileMenu = new JMenu("查找结果");
		   	JMenuItem SaveMenu= new JMenuItem("保存");
		      SaveMenu.addActionListener(new SaveActionListener());
		      JMenuItem CloseMenu= new JMenuItem("关闭");
		      CloseMenu.addActionListener(new CloseActionListener());
		      FileMenu.add(SaveMenu);
		      FileMenu.add(CloseMenu);
		      menubar.add(FileMenu);
		      
				content= new JTable(ta,columnTitle);
				content.setPreferredScrollableViewportSize(new Dimension(800, 600));
				content.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
				
			    TableColumn column = null;  
	   		    
				column = content.getColumnModel().getColumn(0);
				column.setPreferredWidth(130);
				column = content.getColumnModel().getColumn(1);
				column.setPreferredWidth(110);
				column = content.getColumnModel().getColumn(2);
				column.setPreferredWidth(50);
				column = content.getColumnModel().getColumn(3);
				column.setPreferredWidth(180);
				column = content.getColumnModel().getColumn(4);
				column.setPreferredWidth(130);
				column = content.getColumnModel().getColumn(5);
				column.setPreferredWidth(40);
				column = content.getColumnModel().getColumn(6);
				column.setPreferredWidth(160);

				
				DefaultTableCellRenderer   r   =   new   DefaultTableCellRenderer();   
				r.setHorizontalAlignment(JLabel.CENTER);   
				content.setDefaultRenderer(Object.class,   r);

				con.add(jScrollPane = new JScrollPane(content));

				con.setBorder(BorderFactory.createEmptyBorder(0,20,30,20));
				this.setSize(900, 700);
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

}
