package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import gloable.LogDataItem;

public class Nginxlog implements LogReadIFace {

	@Override
	public void readAndAnalysis(String fileName) {
		// TODO Auto-generated method stub
		String tempString = null;
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
        	 reader = new BufferedReader(new FileReader(file));
             LogDataItem iData=new LogDataItem();
             //IPLocate ipL = new IPLocate();
             while ((tempString = reader.readLine()) != null) {
            	 
            	 String[] tempstring_array = tempString.split(" ");
            	 iData.client_ip = tempstring_array[0];
            	 iData.identified_name = tempstring_array[2];
            	 iData.date = tempstring_array[3].replace("[","");
            	 iData.request_method = tempstring_array[5].replace("\"","");
            	 iData.url_query = tempstring_array[6];
            	 iData.http_protocol = tempstring_array[7].replace("\"","");
            	 iData.status = tempstring_array[8];
            	 iData.server_package = tempstring_array[9];
            	 iData.http_referer = tempstring_array[10];
            	 
            	 StringBuffer UserAgent = new StringBuffer();//将客户端系统信息
            	 
            	 for(int i = 11;i < tempstring_array.length ;i ++)
            		 UserAgent.append(tempstring_array[i].replace("\"",""));
                
                 iData.User_Agent=UserAgent.toString();
                 //IP定位模块
                 /**
            	 String address = "";
            	 address = ipL.getAddresses("ip="+iData.client_ip, "utf-8");
            	 iData.address_full = address.replace("[", "").replace("]", "");
 	      	     int i = address.indexOf("[");
 	      	     int j = address.indexOf("]");
 	      	     iData.address_city = address.substring(i+1, j);
            	 ***/
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
