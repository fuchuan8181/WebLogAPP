package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import gloable.LogDataItem;
import gloable.MiddleDataVector;

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
			while((tempstring = reader.readLine()) != null){
				String[] tempstring_array = tempstring.split(" ");
				
				iData.client_ip =  tempstring_array[0];
				iData.isIdentityCheck = tempstring_array[1] + tempstring_array[2];
				iData.date = tempstring_array[3].replace("[", "") + tempstring_array[4].replace("]", "");
				iData.request_method = tempstring_array[5].replace("\"", "");
				iData.url_request = tempstring_array[6];
				iData.http_protocol = tempstring_array[7].replace("\"", "");
				iData.status = tempstring_array[8];
				iData.server_package = tempstring_array[9];
				String ua = "";
				for(int i = 11; i < 23; i++){
					ua += tempstring_array[i].replace("\"", "");
				}
				iData.User_Agent = ua;
				
				//测试
				System.out.println(iData.client_ip);
				System.out.println(iData.isIdentityCheck);
				System.out.println(iData.date);
				System.out.println(iData.request_method);
				System.out.println(iData.url_request);
				System.out.println(iData.http_protocol);
				System.out.println(iData.status);
				System.out.println(iData.server_package);
				System.out.println(iData.User_Agent);
				
				MiddleDataVector vector=MiddleDataVector.getInstance();
            	vector.addElement(iData);//存入中间变量Vector
			}
			reader.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
