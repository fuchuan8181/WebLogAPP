package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import gloable.Month;
import gloable.LogDataItem;
import gloable.MiddleDataVector;

public class IISLog implements LogReadIFace{

	public void readAndAnalysis(String fileName) {
		// TODO Auto-generated method stub
		/****
		 * 示例
		 * MiddleDataVector instance=MiddleDataVector.getInstance();
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
            boolean getformat = false;//确认日志格式已传入
            
         // 该正则表达式筛选出日志具体内容
            String tempString = null;
            String pattern_file = "(#)(.*)";
            
            //该正则表达式筛选出格式信息
            //PS：虽然格式信息固定在第四行，
            String pattern_format="(#Fields:)(.*)";
            
         // 创建 Pattern 对象
            Pattern r_file = Pattern.compile(pattern_file);
            Pattern r_format = Pattern.compile(pattern_format);
            
            //找到格式信息
            while ((tempString = reader.readLine()) != null) {
            	Matcher m_format = r_format.matcher(tempString);
            	if(m_format.find())//为日志格式信息
            	{
            		//去除首尾空格将格式信息以空格分割成字符串
            		 String formatstring[] = m_format.group(2).trim().split(" ");
            		 
                	 //如果formatstring[0]为date，formatstring[1]为time，那么 iData.date等于splitstring[0]+splitstring[1]
            		 //设置int something表示formatstring中的位数
            		 for (int i = 0; i < formatstring.length; i++) {
                         switch(formatstring[i]){//匹配并存储int类型各参数位置数据
                         
                         case "date":iData.date_num = i;
                         case "time":iData.time_num = i;
                         case "s-ip":iData.server_ip_num = i;
                         case "cs-method":iData.request_method_num = i;
                         case "cs-uri-stem":iData.url_query_request_num = i;
                         case"cs-uri-query":iData.url_query_query_num = i;
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
            
            if( getformat == true)//确认格式信息已存入方可进行具体信息读入
            {
            //因系统信息均在文件初始位置，得到格式信息后可继续读入，不必改变文件读入指针位置
            // 继续读入文件，直到读入null为文件结束
           while ((tempString = reader.readLine()) != null) {

                Matcher m_file = r_file.matcher(tempString);
                
                if (!m_file.find( )) {

                    //日志信息存入中间向量
                	 String tempstring_array[] = tempString.split(" ");//将读入文件行信息进行分割\
                	 String datestring[] =tempstring_array[iData.date_num].split("-") ;
                	 Month mon = null;
                	 
                     StringBuffer date = new StringBuffer();//将date和time信息拼接形成本系统标准时间信息
                     
                     date.append(datestring[2]);
         		    date.append("/");
         		    date.append(mon.getName(Integer.valueOf(datestring[1]).intValue()));
         		    date.append("/");
         		    date.append(datestring[0]);
         		    date.append(":");
                     date.append(tempstring_array[iData.time_num]);
                    
                     iData.date=date.toString();
                	 iData.server_ip=tempstring_array[iData.server_ip_num];//服务器ip
                	 iData. request_method=tempstring_array[iData.request_method_num];//请求方式
                	 
                	 StringBuffer url = new StringBuffer();//
                	 url.append(tempstring_array[iData.url_query_request_num]);
                	 url.append(tempstring_array[iData.url_query_query_num]);
                	 
                	 iData.url_request=url.toString();//请求的url，被访问的资源        	 
                	 iData. server_port=tempstring_array[iData.server_port_num];//服务器端口： 服务端提供服务的传输层端口
                	 iData.client_ip=tempstring_array[iData.client_ip_num];//客户端ip
                	 iData.User_Agent=tempstring_array[iData.User_Agent_num];//客户端所用的浏览器版本信息
                	 iData. status=(tempstring_array[iData.status_num]);//行为执行后的返回状态
                	 //测试用例
                	 /****
                	 System.out.println(iData.date);
                	 System.out.println(iData.server_ip);
                	 System.out.println(iData.request_method);
                	 System.out.println(iData.url_request);
                	 System.out.println(iData.server_port);
                	 System.out.println(iData.client_ip);
                	 System.out.println(iData.User_Agent);
                	 System.out.println(iData.status);
                	 ***/

                	 MiddleDataVector vector=MiddleDataVector.getInstance();
                	 vector.addElement(iData);//存入中间变量Vector
                	 
                 }
            }
            }else//若未发现格式信息，则报告错误弹窗
            {
            	JOptionPane.showMessageDialog(null, "该日志文件格式有误!", "错误信息",
                        JOptionPane.ERROR_MESSAGE);
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
