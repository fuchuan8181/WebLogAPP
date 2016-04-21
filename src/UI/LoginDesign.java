package UI;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Gloable.globleStatus;
import Logic.loginconfirm;


public class LoginDesign {
	static JButton confirm;
	static JTextField username=new JTextField(15);
	static JPasswordField password=new JPasswordField(15);
	static JLabel user = new JLabel("�û���");
	static JLabel pass = new JLabel("����");
	static JLabel error = new JLabel("       ");
	static JFrame LoginFrame = new JFrame("��¼����");//������
	
	
	public  void createLoginUI()
	{
		ImageIcon bg = new ImageIcon("picture//background.jpeg");
		JLabel imgLabel = new JLabel(bg);
		LoginFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirm = new JButton("ȷ��");
        confirm.addActionListener(new confirmActionListener());
        imgLabel.setBounds(0,0,bg.getIconWidth(), bg.getIconHeight());//���ñ�����ǩ��λ��
        
        Container LoginPanel=LoginFrame.getContentPane();  

          

        GridBagLayout layout = new GridBagLayout();
        LoginPanel.setLayout(layout);
        LoginPanel.add(user);
        LoginPanel.add(username);
        LoginPanel.add(pass);
        LoginPanel.add(password);
        LoginPanel.add(confirm);
        LoginPanel.add(error);

        
        GridBagConstraints s_layout= new GridBagConstraints();
        s_layout.fill = GridBagConstraints.CENTER;
        s_layout.insets = new Insets(300,0,0,5);//top, left,  bottom, right
        s_layout.gridwidth=1;//�÷������������ˮƽ��ռ�õĸ����������Ϊ0����˵��������Ǹ��е����һ��
        layout.setConstraints(user, s_layout);
        
        s_layout.insets = new Insets(300,0,0,20);
        s_layout.gridwidth=1;
        layout.setConstraints(username, s_layout);
        
        s_layout.insets = new Insets(300,0,0,5);
        s_layout.gridwidth=1;
        layout.setConstraints(pass, s_layout);
        
        s_layout.insets = new Insets(300,0,0,10);
        s_layout.gridwidth=1;
        layout.setConstraints(password, s_layout);
        
        s_layout.insets = new Insets(300,0,0,0);
        s_layout.gridwidth=0;
        layout.setConstraints(confirm, s_layout);
        
        s_layout.anchor= GridBagConstraints.WEST ;
        s_layout.insets = new Insets(10,0,0,0);
        s_layout.gridwidth=0;
        layout.setConstraints(error, s_layout);
        
        ((JPanel)LoginPanel).setOpaque(false); //ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������ 
        LoginFrame.setSize(658, 439);
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
        	char[] pass = password.getPassword();
        	String p = new String(pass);
        	StringBuffer login_info = new StringBuffer();
        	login_info.append(u);
        	login_info.append(" ");
        	login_info.append(p);
        	loginconfirm con_login = new loginconfirm(login_info.toString());
            }

    }
}
