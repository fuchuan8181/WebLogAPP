package ui;

public class mainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {//事件派发线程
	            public void run() {
		MyDialog myDlg=new MyDialog();
		myDlg.createAndShowGUI();//创建UI界面

	            }
	        });
	}

}
