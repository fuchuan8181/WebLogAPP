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
		 * ʾ��
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
            System.out.println("���ļ�~��ʼ~��");
            reader = new BufferedReader(new FileReader(file));
            
            LogDataItem iData;
            globleStatus data_num = new globleStatus();
            boolean getformat = false;//ȷ����־��ʽ�Ѵ���
            
         // ��������ʽɸѡ����־��������
            String tempString = null;
            String pattern_file = "(#)(.*)";
            
            //��������ʽɸѡ����ʽ��Ϣ
            //PS����Ȼ��ʽ��Ϣ�̶��ڵ����У�
            String pattern_format="(#Fields:)(.*)";
            
         // ���� Pattern ����
            Pattern r_file = Pattern.compile(pattern_file);
            Pattern r_format = Pattern.compile(pattern_format);
            
            //�ҵ���ʽ��Ϣ
            while ((tempString = reader.readLine()) != null) {
            	Matcher m_format = r_format.matcher(tempString);
            	if(m_format.find())//Ϊ��־��ʽ��Ϣ
            	{
            		//ȥ����β�ո񽫸�ʽ��Ϣ�Կո�ָ���ַ���
            		 String formatstring[] = m_format.group(2).trim().split(" ");
            		 
                	 //���formatstring[0]Ϊdate��formatstring[1]Ϊtime����ô iData.date����splitstring[0]+splitstring[1]
            		 //����int something��ʾformatstring�е�λ��
            		 for (int i = 0; i < formatstring.length; i++) {
                         switch(formatstring[i]){//ƥ�䲢�洢int���͸�����λ������
                         
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
            
            if( getformat == true)//ȷ�ϸ�ʽ��Ϣ�Ѵ��뷽�ɽ��о�����Ϣ����
            {
            //��ϵͳ��Ϣ�����ļ���ʼλ�ã��õ���ʽ��Ϣ��ɼ������룬���ظı��ļ�����ָ��λ��
            // ���������ļ���ֱ������nullΪ�ļ�����
           
         getAddress getaddr = new getAddress();
      	 MiddleDataVector list=MiddleDataVector.getInstance();
            	
           while ((tempString = reader.readLine()) != null) {

                Matcher m_file = r_file.matcher(tempString);
                
                if (!m_file.find( )) {

                    //��־��Ϣ�����м�����
                	 String tempstring_array[] = tempString.split(" ");//�������ļ�����Ϣ���зָ�\
                	 String datestring[] =tempstring_array[data_num.date_num].split("-") ;

                	 
                     StringBuffer date = new StringBuffer();//��date��time��Ϣƴ���γɱ�ϵͳ��׼ʱ����Ϣ
                     
                     date.append(datestring[2]);
         		     date.append("/");
         		     date.append(Month.getName(Integer.valueOf(datestring[1]).intValue()));
         		     date.append("/");
         		     date.append(datestring[0]);
         		     date.append(":");
                     date.append(tempstring_array[data_num.time_num]);
                     
                     iData=new LogDataItem();
                     iData.date=date.toString();
                	 iData.server_ip=tempstring_array[data_num.server_ip_num];//������ip
                	 iData. request_method=tempstring_array[data_num.request_method_num];//����ʽ
                	 

                	 iData.url_stem = tempstring_array[data_num.url_stem_num];             	 
                	 iData.url_query=tempstring_array[data_num.url_query_num];//�����url�������ʵ���Դ        	 
                	 iData. server_port=tempstring_array[data_num.server_port_num];//�������˿ڣ� ������ṩ����Ĵ����˿�
                	 iData.client_ip=tempstring_array[data_num.client_ip_num];//�ͻ���hip
                	 iData.User_Agent=tempstring_array[data_num.User_Agent_num];//�ͻ������õ�������汾��Ϣ
                	 iData. status=(tempstring_array[data_num.status_num]);//��Ϊִ�к�ķ���״̬
                	 //ip��λ
                	 iData.address_full = getaddr.get_full(iData.client_ip);
                	 iData.address_city = getaddr.get_city();
                	 iData.address_ips = getaddr.get_isp(iData.client_ip);
                	
     	      	     //��������
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
     	      	     
                	 list.addElement(iData);//�����м����Vector
                 }
              }
            }else//��δ���ָ�ʽ��Ϣ���򱨸���󵯴�
            {
            	JOptionPane.showMessageDialog(null, "����־�ļ���ʽ����!", "������Ϣ",
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