package ui;


import java.awt.BorderLayout;

import gloable.globleStatus;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;



public class Demo extends JPanel {
	//JLabel filepath;//��ʾ�ı���ͼ�񣬻����߼����
	
	JButton getfilepath;//��ȡ�ļ�·��
	JButton Analysis;//�����ļ�
	
	static javax.swing.JFileChooser MyFile=new javax.swing.JFileChooser();
	
	public Demo()
	{
        super(new BorderLayout());//���ø���Ĺ��췽��
        
        String[] formatStrings = { "IIS", "Apache", "Nginx", "Tomcat" };
        final globleStatus iglobleStatus = new globleStatus();
 
        //���� combo box, Ĭ��Ϊ��1��ѡ��.

        final JComboBox formatList = new JComboBox(formatStrings);
        formatList.setSelectedIndex(0);//�趨Ĭ����ʾ��index
        iglobleStatus.setFileType(formatList.getSelectedIndex());
        
        //���ü�����ѡ�����ȫ��״̬�е���־��ʽ
        formatList.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e) {  
                int petName = formatList.getSelectedIndex();
                //System.out.printf("��ѡ����%d",petName);
                iglobleStatus.setFileType(petName);
            }  
        });
        
        // ������ť
         getfilepath = new JButton("ѡ����־�ļ�");
         getfilepath.addActionListener(new GetfilepathActionListener());
         getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
         Analysis = new JButton("���벢����");
         Analysis.addActionListener(new analysisActionListener());
         Analysis.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         
 
        //��ʱû����
        /*filepath = new JLabel();
        filepath.setFont(filepath.getFont().deriveFont(Font.ITALIC));//���������С
        filepath.setHorizontalAlignment(JLabel.CENTER);//���ñ�ǩ������ X ��Ķ��뷽ʽ

        filepath.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));//����һ��ռ�ÿռ䵫û�л��ƵĿձ߿�ָ���˶��ߡ����ߡ���߿��ߺ��ұ߿��ߵĿ�ȡ�
        filepath.setPreferredSize(new Dimension(177, 122));//���ô��������ѡ��С��
        */
 
        //���֣����ؼ�������Jpanel��panel��Ϊһ������ʹ��
        //Jpanel��һ����̬�������������������ʾһ�о�̬��Ϣ�����ܽ����û������롣�����������JFrame�����Ķ��������ϲ��������
        add(formatList, BorderLayout.PAGE_START);//��������ڵ�һ�в�������֮ǰ
        add(getfilepath,BorderLayout.CENTER);
        add(Analysis,BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
	}
	


//��ť��������
	private class GetfilepathActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("�㰴�˰�ť");   
            MyFile.showOpenDialog(null);  //�����ļ�ѡ��
            File f = MyFile.getSelectedFile();  //�õ�
            if(f != null){
                globleStatus IglobleStatus = new globleStatus();
                IglobleStatus.setFilename(f.getAbsolutePath());//����־�ľ���·����ȫ��״̬
                

            }
        } 
    }
	
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("�㰴��ȷ��");   
            globleStatus theglostatus=new globleStatus();
            String theFilename=theglostatus.getFilename();
            if(theFilename != null)
            {
                MyDialog iDialog = new MyDialog();
                iDialog.run();//run�����Ǹ���һ���ڶ�����������������
            }
            else 
            {
            	 JOptionPane.showMessageDialog(null, "����ѡ����־�ļ�!", "������Ϣ",
                         JOptionPane.ERROR_MESSAGE);
            }
                
            }

    }


    }
    



