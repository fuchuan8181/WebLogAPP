package ui;

public class mainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {//�¼��ɷ��߳�
	            public void run() {
		MyDialog myDlg=new MyDialog();
		myDlg.createAndShowGUI();
		//myDlg.setVisible(true);
		//myDlg.run();
	            }
		

	        });
	}

}
