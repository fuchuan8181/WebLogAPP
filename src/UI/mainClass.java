package UI;

import javax.swing.JFrame;

import Gloable.globleStatus;

public class mainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {//�¼��ɷ��߳�
	            public void run() {
	            	LoginDesign aDesign = new LoginDesign();
	            	aDesign.createLoginUI();
	            	//if(globleStatus.login)
	            		//MyDialog.createAndShowGUI();//����UI����
	            }
	        });
	}

}
