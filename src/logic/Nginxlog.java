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
             while ((tempString = reader.readLine()) != null) {
            	 
            	 String[] tempstring_array = tempString.split(" ");
            	 iData.client_ip = tempstring_array[0];
            	 
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
