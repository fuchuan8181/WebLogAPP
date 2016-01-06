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
            
            LogDataItem iData=new LogDataItem();
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
            
            if( getformat == true)//ȷ�ϸ�ʽ��Ϣ�Ѵ��뷽�ɽ��о�����Ϣ����
            {
            //��ϵͳ��Ϣ�����ļ���ʼλ�ã��õ���ʽ��Ϣ��ɼ������룬���ظı��ļ�����ָ��λ��
            // ���������ļ���ֱ������nullΪ�ļ�����
           while ((tempString = reader.readLine()) != null) {

                Matcher m_file = r_file.matcher(tempString);
                
                if (!m_file.find( )) {

                    //��־��Ϣ�����м�����
                	 String tempstring_array[] = tempString.split(" ");//�������ļ�����Ϣ���зָ�\
                	 String datestring[] =tempstring_array[iData.date_num].split("-") ;
                	 Month mon = null;
                	 
                     StringBuffer date = new StringBuffer();//��date��time��Ϣƴ���γɱ�ϵͳ��׼ʱ����Ϣ
                     
                     date.append(datestring[2]);
         		    date.append("/");
         		    date.append(mon.getName(Integer.valueOf(datestring[1]).intValue()));
         		    date.append("/");
         		    date.append(datestring[0]);
         		    date.append(":");
                     date.append(tempstring_array[iData.time_num]);
                    
                     iData.date=date.toString();
                	 iData.server_ip=tempstring_array[iData.server_ip_num];//������ip
                	 iData. request_method=tempstring_array[iData.request_method_num];//����ʽ
                	 
                	 StringBuffer url = new StringBuffer();//
                	 url.append(tempstring_array[iData.url_query_request_num]);
                	 url.append(tempstring_array[iData.url_query_query_num]);
                	 
                	 iData.url_request=url.toString();//�����url�������ʵ���Դ        	 
                	 iData. server_port=tempstring_array[iData.server_port_num];//�������˿ڣ� ������ṩ����Ĵ����˿�
                	 iData.client_ip=tempstring_array[iData.client_ip_num];//�ͻ���ip
                	 iData.User_Agent=tempstring_array[iData.User_Agent_num];//�ͻ������õ�������汾��Ϣ
                	 iData. status=(tempstring_array[iData.status_num]);//��Ϊִ�к�ķ���״̬
                	 //��������
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
                	 vector.addElement(iData);//�����м����Vector
                	 
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
