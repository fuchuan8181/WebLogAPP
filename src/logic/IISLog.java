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
