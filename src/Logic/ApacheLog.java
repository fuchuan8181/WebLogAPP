package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;

public class ApacheLog implements LogReadIFace{
	@Override
	public void readAndAnalysis(String fileName) {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		LogDataItem iData = new LogDataItem();

		try {
			System.out.println("开始读取文件");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempstring = null;
			//IPLocate ipL = new IPLocate();
			while((tempstring = reader.readLine()) != null){
				String[] tempstring_array = tempstring.split(" ");
				
				iData.client_ip =  tempstring_array[0];
				iData.isIdentityCheck = tempstring_array[1] + tempstring_array[2];

		    	DateFormat df = new SimpleDateFormat("[dd/MMM/yyyy:hh:mm:ss", Locale.ENGLISH);
				iData.date = df.parse(tempstring_array[3]);
				iData.request_method = tempstring_array[5].replace("\"", "");
				iData.url_query = tempstring_array[6];
				iData.http_protocol = tempstring_array[7].replace("\"", "");
				iData.status = tempstring_array[8];
				iData.server_package = tempstring_array[9];
				String ua = "";
				for(int i = 11; i < 23; i++){
					ua += tempstring_array[i].replace("\"", "");
				}
				iData.User_Agent = ua;
				
				//IP定位模块
				/***
           	 	String address = "";
           	 	address = ipL.getAddresses("ip="+iData.client_ip, "utf-8");
           	 	iData.address_full = address.replace("[", "").replace("]", "");
	      	    int i = address.indexOf("[");
	      	    int j = address.indexOf("]");
	      	    iData.address_city = address.substring(i+1, j);
				****/
				//测试
				/**
				System.out.println(iData.client_ip);
				System.out.println(iData.isIdentityCheck);
				System.out.println(iData.date);
				System.out.println(iData.request_method);
				System.out.println(iData.url_request);
				System.out.println(iData.http_protocol);
				System.out.println(iData.status);
				System.out.println(iData.server_package);
				System.out.println(iData.User_Agent);
				System.out.println(iData.address_full);
     	      	System.out.println(iData.address_city);
				**/
				
				MiddleDataVector vector=MiddleDataVector.getInstance();
            	vector.addElement(iData);//存入中间变量Vector            	
			}
			reader.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
