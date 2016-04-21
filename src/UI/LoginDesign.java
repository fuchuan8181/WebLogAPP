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
	static JLabel user = new JLabel("用户名");
	static JLabel pass = new JLabel("密码");
	static JLabel error = new JLabel("       ");
	static JFrame LoginFrame = new JFrame("登录界面");//构造框架
	
	
	public  void createLoginUI()
	{
		ImageIcon bg = new ImageIcon("picture//background.jpeg");
		JLabel imgLabel = new JLabel(bg);
		LoginFrame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirm = new JButton("确定");
        confirm.addActionListener(new confirmActionListener());
        imgLabel.setBounds(0,0,bg.getIconWidth(), bg.getIconHeight());//设置背景标签的位置
        
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
        s_layout.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
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
        
        ((JPanel)LoginPanel).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。 
        LoginFrame.setSize(658, 439);
        LoginFrame.setVisible(true);//设为可见
	}
	
	public static void closelogin()
	{
		LoginFrame.setVisible(false);//设为不可见
}
	public static void shorerror()
	{
		error.setText("用户名或密码错误!");
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
