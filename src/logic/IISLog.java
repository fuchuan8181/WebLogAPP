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
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
            reader = new BufferedReader(new FileReader(file));
            
         // ��ָ��ģʽ���ַ�������
            String tempString = null;
            String pattern = "(#)(.*)";
            
         // ���� Pattern ����
            Pattern r = Pattern.compile(pattern);
            
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                // ��ʾ�к�
            	// ���ڴ��� matcher ����
                Matcher m = r.matcher(tempString);
                
                if (m.find( )) {
                	//Ϊ�汾��Ϣ��������
                 } else {
                    //��־��Ϣ�����м�����
                	 //���д��Խ��Խ���ø�Ц
                	 //һ����ĵġ�����
                	 String b[] = tempString.split(" ");
                	 LogDataItem iData=new LogDataItem();
                	 
                     StringBuffer date = new StringBuffer();
                     for (int i = 0; i < 2; i++) {
                         date.append(String.valueOf(i));
                     }
                     iData.data=date.toString();
                	 iData.server_ip=b[3];
                	 iData. request_method=b[5];//����ʽ
                	 iData.uri_request=b[6];//�����url�������ʵ���Դ
                	 
                	 int i=Integer.parseInt(b[8]);
                	 
                	 iData. server_port=i;//�������˿ڣ� ������ṩ����Ĵ����˿�
                	 iData.client_ip=b[10];//�ͻ���ip
                	 iData.User_Agent=b[11];//�ͻ������õ�������汾��Ϣ
                	 
                	 i=Integer.parseInt(b[12]);
                	 iData. status=(i);//��Ϊִ�к�ķ���״̬


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
