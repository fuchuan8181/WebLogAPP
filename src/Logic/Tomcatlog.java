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

public class Tomcatlog implements LogReadIFace {
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
				
				iData.client_ip = tempstring_array[0];
				iData.server_ip = tempstring_array[1];
				iData.package_sent = tempstring_array[2];
				iData.package_sent_excludingHTTPheaders = tempstring_array[3];
				iData.server_name = tempstring_array[4];
				iData.http_protocol = tempstring_array[5];
				iData.identity_name = tempstring_array[6];
				iData.request_method = tempstring_array[7];
				iData.server_port =tempstring_array[8];
				iData.querystring = tempstring_array[9].replace(";", "");
				iData.status = tempstring_array[13];
				iData.session_ID = tempstring_array[14];
		    	DateFormat df = new SimpleDateFormat("[dd/MMM/yyyy:hh:mm:ss", Locale.ENGLISH);
				iData.date = df.parse(tempstring_array[15]);

				iData.identified_name = tempstring_array[17];
				iData.url_query = tempstring_array[18];
				iData.domain_name = tempstring_array[19];
				iData.time_request_inMillis = tempstring_array[21];
				
				
				//IP定位模块
				/**
           	 	String address = "";
           	 	address = ipL.getAddresses("ip="+iData.client_ip, "utf-8");
           	 	iData.address_full = address.replace("[", "").replace("]", "");
	      	    int i = address.indexOf("[");
	      	    int j = address.indexOf("]");
	      	    iData.address_city = address.substring(i+1, j);
	      	    ***/
				
				//测试
				/**
	      	    System.out.println(iData.client_ip);
				System.out.println(iData.server_ip);
				System.out.println(iData.package_sent);
				System.out.println(iData.package_sent_excludingHTTPheaders);
				System.out.println(iData.server_name);
				System.out.println(iData.http_protocol);
				System.out.println(iData.identity_name);
				System.out.println(iData.request_method);
				System.out.println(iData.server_port);
				System.out.println(iData.querystring);
				System.out.println(iData.status);
				System.out.println(iData.session_ID);
				System.out.println(iData.date);
				System.out.println(iData.identified_name);
				System.out.println(iData.url_request);
				System.out.println(iData.domain_name);
				System.out.println(iData.time_request_inMillis);
				System.out.println(iData.address_full);
     	      	System.out.println(iData.address_city);
				**/
				
				MiddleDataVector vector=MiddleDataVector.getInstance();
           	 	vector.addElement(iData);//存入中间变量Vector
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
