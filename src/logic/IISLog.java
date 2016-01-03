package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gloable.LogDataItem;
import gloable.MiddleDataVector;

public class IISLog implements LogReadIFace{

	public void readAndAnalysis(String fileName) {
		// TODO Auto-generated method stub
		/*MiddleDataVector instance=MiddleDataVector.getInstance();
		for (int i=0;i<3;i++)
		{
			LogDataItem lItem=new LogDataItem();
			lItem.bSQL=true;
			lItem.attr1="111";
			instance.addElement(lItem);
		}	*/
		
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("读文件~开始~：");
            reader = new BufferedReader(new FileReader(file));
            
            LogDataItem iData=new LogDataItem();
            boolean getformat = false;
            
         // 按指定模式在字符串查找
            String tempString = null;
            String pattern_file = "(#)(.*)";
            
            String pattern_format="(#Fields:)(.*)";
            
         // 创建 Pattern 对象
            Pattern r_file = Pattern.compile(pattern_file);
            Pattern r_format = Pattern.compile(pattern_format);
            
            //找到格式信息
            while ((tempString = reader.readLine()) != null) {
            	Matcher m_format = r_format.matcher(tempString);
            	if(m_format.find())//为版本信息
            	{
            		 String formatstring[] = m_format.group(2).trim().split(" ");
            		 
            		 for (int j = 0; j < formatstring.length; j++) {
            			 System.out.println(formatstring[j]);
            		 }
            		 
                	 //如果formatstring[0]为date，formatstring[1]为time，那么 iData.date等于splitstring[0]+splitstring[1]
            		 //设置int something表示formatstring中的位数
            		 for (int i = 0; i < formatstring.length; i++) {
                         switch(formatstring[i]){
                         
                         case "date":iData.date_num = i;

                         case "time":iData.time_num = i;
                         case "s-ip":iData.server_ip_num = i;
                         case "cs-method":iData.request_method_num = i;
                         case "cs-uri-stem":iData.uri_request_num = i;
                         case "s-port":iData.server_port_num = i;
                         case "c-ip":iData.client_ip_num = i;
                         case "cs(User-Agent)":iData.User_Agent_num = i;
                         case "sc-status":iData.status_num = i;

                         }
                     }
            		 getformat = true;
            		 break;
            	}
            }

            // 一次读入一行，直到读入null为文件结束
           while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	// 现在创建 matcher 对象
                Matcher m_file = r_file.matcher(tempString);
                
                if (!m_file.find( ) && getformat == true) {

                    //日志信息存入中间向量

                	 String splitstring[] = tempString.split(" ");
                	 
                     StringBuffer date = new StringBuffer();
					date.append(splitstring[iData.date_num]);
                     date.append(" ");
                     date.append(splitstring[iData.time_num]);
                    
                     iData.date=date.toString();
                	 iData.server_ip=splitstring[iData.server_ip_num];
                	 iData. request_method=splitstring[iData.request_method_num];//请求方式
                	 iData.uri_request=splitstring[iData.uri_request_num];//请求的url，被访问的资源        	 
                	 iData. server_port=splitstring[iData.server_port_num];//服务器端口： 服务端提供服务的传输层端口
                	 iData.client_ip=splitstring[iData.client_ip_num];//客户端ip
                	 iData.User_Agent=splitstring[iData.User_Agent_num];//客户端所用的浏览器版本信息
                	 iData. status=(splitstring[iData.status_num]);//行为执行后的返回状态
                	 
                	 System.out.println( iData.date);
                	 System.out.println(iData.server_ip);
                	 System.out.println(iData.request_method);
                	 System.out.println(iData.uri_request);
                	 System.out.println(iData.server_port);
                	 System.out.println(iData.client_ip);
                	 System.out.println(iData.User_Agent);
                	 System.out.println(iData.status);

                	 MiddleDataVector vector=MiddleDataVector.getInstance();
                	 vector.addElement(iData);
                	 
                 }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}

}
