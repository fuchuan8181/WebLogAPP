package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeSelect {
	
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
	
	public TimeSelect()
	{  
		JDialog time = new JDialog();
    	JPanel con = new JPanel();
    	boolean fromLeap  = true;
    	boolean toLeap  = true;
    	
    	JButton ensure = new JButton("确定");
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
		    //System.out.println(m);

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
        con.setLayout(layout);
        con.add(from);
        con.add(yearList);
        con.add(ye);
        con.add(monthList);
        con.add(mo);
        con.add(dayList);
        con.add(da);
        con.add(hourList);
        con.add(ho);
        con.add(minuteList);
        con.add(mi);
        con.add(secondList);
        con.add(se);
        
        con.add(to);

        con.add(yearList2);
        con.add(ye2);
        con.add(monthList2);
        con.add(mo2);
        con.add(dayList2);
        con.add(da2);
        con.add(hourList2);
        con.add(ho2);
        con.add(minuteList2);
        con.add(mi2);
        con.add(secondList2);
        con.add(se2);
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
	public static boolean leap(int i)
	{
		if(i%4==0 && i%100!=0 || i%400==0)
		    return true;
		else
			return false;
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
