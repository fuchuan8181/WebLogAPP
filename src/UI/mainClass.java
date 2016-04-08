package UI;

import javax.swing.JFrame;

import Gloable.globleStatus;

public class mainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {//事件派发线程
	            public void run() {
	            	LoginDesign aDesign = new LoginDesign();
	            	aDesign.createLoginUI();
	            	//if(globleStatus.login)
	            		//MyDialog.createAndShowGUI();//创建UI界面
	            }
	        });
	}

}
