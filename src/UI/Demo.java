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
	//JLabel filepath;//��ʾ�ı���ͼ�񣬻����߼����
	
	JButton getfilepath;//��ȡ�ļ�·��
	JButton Analysis;//�����ļ�
	JButton showcitychart;//��ȡ�ļ�·��
	JButton showattacktable;//�����ļ�
	JTextField text=new JTextField(20);
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
	public Demo()
	{
		super(new GridBagLayout());
		
		JMenuBar menubar = new JMenuBar();

	   	 JMenu NewMenu = new JMenu("���񴴽�");
	   	 JMenu UpdateMenu = new JMenu("��������");
	   	 JMenu HelpMenu = new JMenu("����");
	   	 
	   	JMenuItem CreateOneMenu= new JMenuItem("�½�����");
       CreateOneMenu.addActionListener(new CreateOneActionListener());
       JMenuItem CreateMultiMenu= new JMenuItem("�½�����ļ�����");
       CreateMultiMenu.addActionListener(new CreateMultiActionListener());
       JMenuItem ExitMenu= new JMenuItem("�˳�");
       JMenuItem UpdatePropertiesMenu= new JMenuItem("����������");
       JMenuItem AboutMenu= new JMenuItem("�汾��Ϣ");
       
       NewMenu.add(CreateOneMenu);
       NewMenu.add(CreateMultiMenu);
       NewMenu.add(ExitMenu);
       UpdateMenu.add(UpdatePropertiesMenu);
       HelpMenu.add(AboutMenu);
       
       menubar.add(NewMenu);
       menubar.add(UpdateMenu);
       menubar.add(HelpMenu);
        
        String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };

 
        //���� combo boxѡ����־��ʽ, Ĭ��Ϊ��1��ѡ��.
        final JComboBox<String> formatList = new JComboBox<String>(formatStrings);
        formatList.setSelectedIndex(0);//�趨Ĭ����ʾ��index
        formatList.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        formatList.setPreferredSize(new Dimension (100,48));
        globleStatus.setFileType(formatList.getSelectedIndex());
        
        //���ü�����ѡ�����ȫ��״̬�е���־��ʽ
        formatList.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e) {  
                int petName = formatList.getSelectedIndex();
                //System.out.printf("��ѡ����%d",petName);
                globleStatus.setFileType(petName);
            }  
        });
        
        // ����ѡ����־�ļ���ť
         getfilepath = new JButton("...");
         getfilepath.addActionListener(new GetfilepathActionListener());
         //getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         //����ִ�з������̰�ť
         Analysis = new JButton("ȷ��");
         Analysis.addActionListener(new analysisActionListener());
         //Analysis.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
         String urlString="C://Users//dell//Desktop//���Ĵ��//log1.png";  
         JLabel label=new JLabel(new ImageIcon(urlString));
         
         JLabel info_1 = new JLabel("Huang Yanyu,All right reserved.");
         JLabel info_2 = new JLabel("China University of Geosciences,2016.");
         
         text.setPreferredSize(new Dimension (270,26));
         
 
        //���֣����ؼ�������Jpanel��panel��Ϊһ������ʹ��
        //Jpanel��һ����̬�������������������ʾһ�о�̬��Ϣ�����ܽ����û������롣�����������JFrame�����Ķ��������ϲ��������
         /******
         GridBagLayout layout = new GridBagLayout();
         this.setLayout(layout);
         this.add(formatList);//�������ӽ�jframe
         GridBagConstraints s_layout= new GridBagConstraints();
       s_layout.fill = GridBagConstraints.BOTH;
       s_layout.gridwidth=1;//�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
       s_layout.weightx = 0;//�÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
       s_layout.weighty=0;//�÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
       layout.setConstraints(formatList, s_layout);//�������
       *****/
         GridBagLayout layout = new GridBagLayout();
         this.setLayout(layout);
         this.add(menubar);
         this.add(label);
         this.add(formatList);//�������ӽ�jframe
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
        s_layout.gridwidth=1;//�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
        s_layout.weightx = 0;//�÷����������ˮƽ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
        s_layout.weighty=0;//�÷������������ֱ��������ȣ����Ϊ0��˵�������죬��Ϊ0�����Ŵ�������������죬0��1֮��
        layout.setConstraints(formatList, s_layout);//�������
        
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
	


//ѡ����־�ļ���ť��������
	private class GetfilepathActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("�㰴�˰�ť");   
            MyFile.showOpenDialog(null);  //�����ļ�ѡ��
            File f = MyFile.getSelectedFile();  //�õ��ļ�
            if(f != null){
                globleStatus.setFilename(f.getAbsolutePath());//����־�ľ���·����ȫ��״̬
               text.setText(globleStatus.getFilename());
               //�����������
               MiddleDataVector vector_clear = MiddleDataVector.getInstance();
               vector_clear.Clear();
           	judgement.num_sql = 0 ;
        	judgement.num_xss = 0 ;
        	judgement.num_exec = 0 ;
        	statistics.Clear();
            }
        } 
    }
	//ִ�з������̰�ť��������
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            //System.out.println("�㰴��ȷ��");   

            String theFilename=globleStatus.getFilename();//�õ��ļ�·��
            if(theFilename != null)
            {
                MyDialog iDialog = new MyDialog();
                iDialog.run();//run�����Ǹ���һ���ڶ�����������������
            }
            else //���ļ�δѡ��򲻴�������ʾ������Ϣ
            {
            	 JOptionPane.showMessageDialog(null, "����ѡ����־�ļ�!", "������Ϣ",
                         JOptionPane.ERROR_MESSAGE);
            }
            }

    }
	
	private static class CreateOneActionListener implements ActionListener{  
	    public void actionPerformed(ActionEvent e) {  
	        //System.out.println("��");   
	       
	    } 
	}
	private static class CreateMultiActionListener implements ActionListener{  
	    public void actionPerformed(ActionEvent e) {  
	        //System.out.println("��");   
	       
	    } 
	}
    }
    



