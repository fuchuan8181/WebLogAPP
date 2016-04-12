package UI;



import java.awt.BorderLayout;
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
	JTextField text=new JTextField(30);
	JRadioButton defaultpath;
	String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };
	
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
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
		super(new BorderLayout());
		
		
		JMenuBar menubar = new JMenuBar();

	   	 JMenu NewMenu = new JMenu("���񴴽�");
	   	 JMenu UpdateMenu = new JMenu("��������");
	   	 JMenu HelpMenu = new JMenu("����");
	   	 
	   	JMenuItem CreateOneMenu= new JMenuItem("�½�����");
       CreateOneMenu.addActionListener(new CreateoneActionListener());
       JMenuItem CreateMultiMenu= new JMenuItem("�½�����ļ�����");
       CreateMultiMenu.addActionListener(new CreatemultiActionListener());
       JMenuItem ExitMenu= new JMenuItem("�˳�");
       ExitMenu.addActionListener(new ExitActionListener());
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
        
        
        JPanel PathChoose = new JPanel();
 
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
                int i = globleStatus.getFileType();
                if(defaultpath.isSelected())
                	setRadioButton(i);
                	
            }  
        });
        
        // ����ѡ����־�ļ���ť
         //getfilepath = new JButton("...");
        // getfilepath.addActionListener(new GetfilepathActionListener());
         //getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         //����ִ�з������̰�ť
         Analysis = new JButton("ȷ��");
         Analysis.addActionListener(new analysisActionListener());
         //Analysis.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
         String urlString="C://Users//dell//Desktop//���Ĵ��//log2.png";  
         JLabel label=new JLabel(new ImageIcon(urlString));
         
         JLabel info_1 = new JLabel("All right reserved.");
         JLabel info_2 = new JLabel("<html><p>ע������:</p><p>&nbsp;&nbsp;&nbsp;&nbsp;��ѡȡ�����־�ļ�ʱ,��ȷ���ļ�������־�ļ���ʱ������.</p><html>");
         text.setPreferredSize(new Dimension (270,26));
         defaultpath = new JRadioButton("Ĭ��·��");
         defaultpath.addActionListener(new defaultActionListener());
 
        //���֣����ؼ�������Jpanel��panel��Ϊһ������ʹ��
        //Jpanel��һ����̬�������������������ʾһ�о�̬��Ϣ�����ܽ����û������롣�����������JFrame�����Ķ��������ϲ��������

         GridBagLayout layout = new GridBagLayout();
        PathChoose.setLayout(layout);
         
        PathChoose.add(formatList);//�������ӽ�jframe
        PathChoose.add(text);

        PathChoose.add(defaultpath);
        PathChoose.add(Analysis);
        PathChoose.add(info_2);
        PathChoose.add(info_1);
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
        
        s_layout.weightx = 0.8;
        layout.setConstraints(text, s_layout);
        layout.setConstraints(defaultpath, s_layout);

       // s_layout.insets = new Insets(20,10,10,0);//	top, left,  bottom, right
        //s_layout.gridwidth=0;
       // layout.setConstraints(getfilepath, s_layout);
        
        s_layout.insets = new Insets(20,10,10,10);
        s_layout.gridwidth=0;
        layout.setConstraints(Analysis, s_layout);
        
        s_layout.insets = new Insets(5,10,10,10);
        s_layout.anchor= GridBagConstraints.WEST ;
        layout.setConstraints(info_1, s_layout);
        layout.setConstraints(info_2, s_layout);
        
        this.add(menubar,BorderLayout.NORTH);
        this.add(label,BorderLayout.CENTER);
        this.add(PathChoose,BorderLayout.SOUTH);
	}
	
	public void setRadioButton(int i)
	{
		switch (i) {
		case 0:
			text.setText("C:\\WINDOWS\\system32\\Logfiles\\");
			break;
		case 1:
			text.setText("<��װĿ¼>\\logs\\");
			break;
		case 2:
			text.setText("<��װĿ¼>\\logs\\access.log�ļ�");
			break;
		case 3:
			text.setText("<��װĿ¼>\\logs\\");
			break;
		default:
			break;
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
	
	
//ѡ����־�ļ���ť��������
	private class CreateoneActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("�㰴�˰�ť");   
            MyFile.showOpenDialog(null);  //�����ļ�ѡ��
            File f = MyFile.getSelectedFile();  //�õ��ļ�
            if(f != null){
                globleStatus.setFilename(f.getAbsolutePath());//����־�ľ���·����ȫ��״̬
               text.setText(globleStatus.getFilename());
               defaultpath.setSelected(false);
               //�����������
               MiddleDataVector vector_clear = MiddleDataVector.getInstance();
               vector_clear.Clear();
           	   judgement.num_sql = 0 ;
        	   judgement.num_xss = 0 ;
        	   judgement.num_exec = 0 ;
               statistics.Clear();
            }
            globleStatus.multi = false;
        } 
    }
	private class CreatemultiActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
           // System.out.println("�㰴�˰�ť");   
        	MyFile.setMultiSelectionEnabled(true);
            MyFile.showOpenDialog(null);  //�����ļ�ѡ��
            File[] f = MyFile.getSelectedFiles();  //�õ��ļ�

            for(int i = 0;i < f.length;i ++){
            if(f != null ){
            	System.out.println(f[i].getAbsolutePath());   
                globleStatus.filenames.add(f[i].getAbsolutePath());//����־�ľ���·����ȫ��״̬
               text.setText(text.getText()+";"+globleStatus.filenames.get(i));
            }
        }
          //�����������
            MiddleDataVector vector_clear = MiddleDataVector.getInstance();
            vector_clear.Clear();
        	judgement.num_sql = 0 ;
     	    judgement.num_xss = 0 ;
     	    judgement.num_exec = 0 ;
     	    statistics.Clear();
            globleStatus.multi = true;
        }
    }
	//ִ�з������̰�ť��������
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            //System.out.println("�㰴��ȷ��");  
            String theFilename;
        	if(globleStatus.multi)
        	{
        		 theFilename=globleStatus.filenames.get(0);//�õ��ļ�·��
        	}
        	else{
        		theFilename=globleStatus.getFilename();//�õ��ļ�·��
        	}
            boolean log = true;

            if(theFilename == null )
            {
            	JOptionPane.showMessageDialog(null, "����ѡ����־�ļ�!", "������Ϣ",
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
            			iDialog.run();//run�����Ǹ���һ���ڶ�����������������
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "��־�ļ���ʽ����!", "������Ϣ",
                                JOptionPane.ERROR_MESSAGE);
					}
            	}
            	else {
            		File f = MyFile.getSelectedFile();
            		 if(f.getName().endsWith(".log"))
            		 {
            			 MyDialog iDialog = new MyDialog();
             			iDialog.run();//run�����Ǹ���һ���ڶ�����������������
            		 }
            		 else{
                     	JOptionPane.showMessageDialog(null, "��־�ļ���ʽ����!", "������Ϣ",
                                 JOptionPane.ERROR_MESSAGE);
                     }
				}
            }
            }
    }
    }
    



