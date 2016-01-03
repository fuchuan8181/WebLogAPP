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
            System.out.println("���ļ�~��ʼ~��");
            reader = new BufferedReader(new FileReader(file));
            
            LogDataItem iData=new LogDataItem();
            boolean getformat = false;
            
         // ��ָ��ģʽ���ַ�������
            String tempString = null;
            String pattern_file = "(#)(.*)";
            
            String pattern_format="(#Fields:)(.*)";
            
         // ���� Pattern ����
            Pattern r_file = Pattern.compile(pattern_file);
            Pattern r_format = Pattern.compile(pattern_format);
            
            //�ҵ���ʽ��Ϣ
            while ((tempString = reader.readLine()) != null) {
            	Matcher m_format = r_format.matcher(tempString);
            	if(m_format.find())//Ϊ�汾��Ϣ
            	{
            		 String formatstring[] = m_format.group(2).trim().split(" ");
            		 
            		 for (int j = 0; j < formatstring.length; j++) {
            			 System.out.println(formatstring[j]);
            		 }
            		 
                	 //���formatstring[0]Ϊdate��formatstring[1]Ϊtime����ô iData.date����splitstring[0]+splitstring[1]
            		 //����int something��ʾformatstring�е�λ��
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

            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
           while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
            	// ���ڴ��� matcher ����
                Matcher m_file = r_file.matcher(tempString);
                
                if (!m_file.find( ) && getformat == true) {

                    //��־��Ϣ�����м�����

                	 String splitstring[] = tempString.split(" ");
                	 
                     StringBuffer date = new StringBuffer();
					date.append(splitstring[iData.date_num]);
                     date.append(" ");
                     date.append(splitstring[iData.time_num]);
                    
                     iData.date=date.toString();
                	 iData.server_ip=splitstring[iData.server_ip_num];
                	 iData. request_method=splitstring[iData.request_method_num];//����ʽ
                	 iData.uri_request=splitstring[iData.uri_request_num];//�����url�������ʵ���Դ        	 
                	 iData. server_port=splitstring[iData.server_port_num];//�������˿ڣ� ������ṩ����Ĵ����˿�
                	 iData.client_ip=splitstring[iData.client_ip_num];//�ͻ���ip
                	 iData.User_Agent=splitstring[iData.User_Agent_num];//�ͻ������õ�������汾��Ϣ
                	 iData. status=(splitstring[iData.status_num]);//��Ϊִ�к�ķ���״̬
                	 
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
