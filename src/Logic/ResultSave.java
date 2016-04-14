package Logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.ResultVector;

public class ResultSave {
public ResultSave(String path,ResultVector vector)
{
	// TODO Auto-generated constructor stub
	try{
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");//�������ڸ�ʽ
		  String a = df.format(new Date());
		  String newline = System.getProperty("line.separator");
		  String str = path +"\\" + a + ".txt";
	      File file =new File(str);

	      //if file doesnt exists, then create it
	      if(!file.exists()){
	       file.createNewFile();
	      }

	      //true = append file
	      DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	      FileWriter fw = new FileWriter(file.getAbsoluteFile());
	      BufferedWriter bw = new BufferedWriter(fw);
    	  bw.write("����ʱ�� ����IP ���󷽷� ����URL��ַ �ύ���� �������� ״̬�� �������Ϣ");
    	  bw.write(newline);
	      for(int i = 0;i < vector.size();i ++)
	      {
	    	  LogDataItem v=(LogDataItem) vector.m_element.get(i);
	    	  bw.write(df2.format(v.date) + " "+v.server_ip+ " "+v.request_method+ " "+v.url_stem+ " "+v.url_query+ " ");
	    	  if(v.bSQL == true)
	    		  bw.write("sqlע�빥��"+ " ");
				else if(v.bXSS == true)
					bw.write("XSS��վ����"+ " ");
				else if(v.bEXEC == true)
					bw.write("��ִ�������"+ " ");
				else{
					bw.write("-"+ " ");
				}
	    	  bw.write(v.status+" "+v.User_Agent);
	    	  bw.write(newline);
	      }
    	  bw.close();
	      System.out.println("Done");
	      JOptionPane.showMessageDialog(null, "����ɹ�!!");
	     }catch(IOException e){
	      e.printStackTrace();
	     }
}
}
