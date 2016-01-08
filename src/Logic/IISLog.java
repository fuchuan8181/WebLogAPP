package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.Month;
import Gloable.globleStatus;
import IPSeeker.IPSeeker;
import IPSeeker.getAddress;

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
            
            LogDataItem iData;
            globleStatus data_num = new globleStatus();
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
                         
                         case "date":data_num.date_num = i;
                         case "time":data_num.time_num = i;
                         case "s-ip":data_num.server_ip_num = i;
                         case "cs-method":data_num.request_method_num = i;
                         case "cs-uri-stem":data_num.url_stem_num = i;
                         case"cs-uri-query":data_num.url_query_num = i;
                         case "s-port":data_num.server_port_num = i;
                         case "c-ip":data_num.client_ip_num = i;
                         case "cs(User-Agent)":data_num.User_Agent_num = i;
                         case "sc-status":data_num.status_num = i;
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
           
         getAddress getaddr = new getAddress();
      	 MiddleDataVector list=MiddleDataVector.getInstance();
            	
           while ((tempString = reader.readLine()) != null) {

                Matcher m_file = r_file.matcher(tempString);
                
                if (!m_file.find( )) {

                    //日志信息存入中间向量
                	 String tempstring_array[] = tempString.split(" ");//将读入文件行信息进行分割\
                	 String datestring[] =tempstring_array[data_num.date_num].split("-") ;

                	 
                     StringBuffer date = new StringBuffer();//将date和time信息拼接形成本系统标准时间信息
                     
                     date.append(datestring[2]);
         		     date.append("/");
         		     date.append(Month.getName(Integer.valueOf(datestring[1]).intValue()));
         		     date.append("/");
         		     date.append(datestring[0]);
         		     date.append(":");
                     date.append(tempstring_array[data_num.time_num]);
                     
                     iData=new LogDataItem();
                     iData.date=date.toString();
                	 iData.server_ip=tempstring_array[data_num.server_ip_num];//服务器ip
                	 iData. request_method=tempstring_array[data_num.request_method_num];//请求方式
                	 

                	 iData.url_stem = tempstring_array[data_num.url_stem_num];             	 
                	 iData.url_query=tempstring_array[data_num.url_query_num];//请求的url，被访问的资源        	 
                	 iData. server_port=tempstring_array[data_num.server_port_num];//服务器端口： 服务端提供服务的传输层端口
                	 iData.client_ip=tempstring_array[data_num.client_ip_num];//客户端hip
                	 iData.User_Agent=tempstring_array[data_num.User_Agent_num];//客户端所用的浏览器版本信息
                	 iData. status=(tempstring_array[data_num.status_num]);//行为执行后的返回状态
                	 //ip定位
                	 iData.address_full = getaddr.get_full(iData.client_ip);
                	 iData.address_city = getaddr.get_city();
                	 iData.address_ips = getaddr.get_isp(iData.client_ip);
                	
     	      	     //测试用例
                	 //System.out.println(iData.date);
                	 //System.out.println(iData.server_ip);
                	 //System.out.println(iData.request_method);
                	 System.out.println(iData.url_stem);
                	System.out.println(iData.url_query);
                	 //System.out.println(iData.server_port);
                	 //System.out.println(iData.client_ip);
                	 //System.out.println(iData.User_Agent);
                	 //System.out.println(iData.status);
                	 System.out.println(iData.address_full);
     	      	     System.out.println(iData.address_city);
     	      	     System.out.println(iData.address_ips);
     	      	     
                	 list.addElement(iData);//存入中间变量Vector
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