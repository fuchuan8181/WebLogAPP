package UI;


import java.awt.BorderLayout;

import javax.swing.BorderFactory;

import javax.swing.JComboBox;

import javax.swing.JPanel;

import Gloable.globleStatus;

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

 
        //���� combo boxѡ����־��ʽ, Ĭ��Ϊ��1��ѡ��.
        final JComboBox<String> formatList = new JComboBox<String>(formatStrings);
        formatList.setSelectedIndex(0);//�趨Ĭ����ʾ��index
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
         getfilepath = new JButton("ѡ����־�ļ�");
         getfilepath.addActionListener(new GetfilepathActionListener());
         getfilepath.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
         //����ִ�з������̰�ť
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
	


//ѡ����־�ļ���ť��������
	private class GetfilepathActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("�㰴�˰�ť");   
            MyFile.showOpenDialog(null);  //�����ļ�ѡ��
            File f = MyFile.getSelectedFile();  //�õ��ļ�
            if(f != null){
                globleStatus.setFilename(f.getAbsolutePath());//����־�ľ���·����ȫ��״̬
               
            }
        } 
    }
	//ִ�з������̰�ť��������
	private class analysisActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
            System.out.println("�㰴��ȷ��");   

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


    }
    



