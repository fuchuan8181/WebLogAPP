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
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");//设置日期格式
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
    	  bw.write("访问时间 访问IP 请求方法 访问URL地址 提交参数 攻击方法 状态码 浏览器信息");
    	  bw.write(newline);
	      for(int i = 0;i < vector.size();i ++)
	      {
	    	  LogDataItem v=(LogDataItem) vector.m_element.get(i);
	    	  bw.write(df2.format(v.date) + " "+v.server_ip+ " "+v.request_method+ " "+v.url_stem+ " "+v.url_query+ " ");
	    	  if(v.bSQL == true)
	    		  bw.write("sql注入攻击"+ " ");
				else if(v.bXSS == true)
					bw.write("XSS跨站攻击"+ " ");
				else if(v.bEXEC == true)
					bw.write("可执行命令攻击"+ " ");
				else{
					bw.write("-"+ " ");
				}
	    	  bw.write(v.status+" "+v.User_Agent);
	    	  bw.write(newline);
	      }
    	  bw.close();
	      System.out.println("Done");
	      JOptionPane.showMessageDialog(null, "保存成功!!");
	     }catch(IOException e){
	      e.printStackTrace();
	     }
}
}
