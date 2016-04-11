package UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gloable.globleStatus;
import Logic.loginconfirm;


public class LoginDesign {
	static JButton confirm;
	static JTextField username=new JTextField(15);
	static JTextField password=new JTextField(15);
	static JLabel user = new JLabel("�û���");
	static JLabel pass = new JLabel("����");
	static JLabel error = new JLabel("       ");
	static JFrame LoginFrame = new JFrame("��¼����");//������
	
	public  void createLoginUI()
	{
		
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirm = new JButton("ȷ��");
        confirm.addActionListener(new confirmActionListener());
        //confirm.addActionListener(new confirmActionListener());
        JPanel LoginPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        LoginPanel.setLayout(layout);
        LoginPanel.add(user);
        LoginPanel.add(username);
        LoginPanel.add(pass);
        LoginPanel.add(password);
        LoginPanel.add(error);
        LoginPanel.add(confirm);
        
        GridBagConstraints s_layout= new GridBagConstraints();
        s_layout.fill = GridBagConstraints.CENTER;
        s_layout.insets = new Insets(20,20,0,0);//top, left,  bottom, right
        s_layout.gridwidth=1;
        layout.setConstraints(user, s_layout);
        
        s_layout.insets = new Insets(20,20,0,20);
        s_layout.gridwidth=0;
        layout.setConstraints(username, s_layout);
        
        s_layout.insets = new Insets(20,20,0,0);
        s_layout.gridwidth=1;
        layout.setConstraints(pass, s_layout);
        
        s_layout.insets = new Insets(20,20,0,20);
        s_layout.gridwidth=0;
        layout.setConstraints(password, s_layout);
        
        s_layout.insets = new Insets(20,0,20,0);
        s_layout.gridwidth=0;
        layout.setConstraints(confirm, s_layout);
        
        s_layout.insets = new Insets(10,0,0,0);
        s_layout.gridwidth=0;
        layout.setConstraints(error, s_layout);
        
        LoginFrame.add(LoginPanel);
        LoginFrame.pack();
        //frame.setSize(400,400);
        LoginFrame.setVisible(true);//��Ϊ�ɼ�
	}
	
	public static void closelogin()
	{
		LoginFrame.setVisible(false);//��Ϊ���ɼ�
}
	public static void shorerror()
	{
		error.setText("�û������������!");
}

	private class confirmActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {  
        	String u = username.getText();
        	String p = password.getText();
        	StringBuffer login_info = new StringBuffer();
        	login_info.append(u);
        	login_info.append(" ");
        	login_info.append(p);
        	loginconfirm con_login = new loginconfirm(login_info.toString());
            }

    }
}
