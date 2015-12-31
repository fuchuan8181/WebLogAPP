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
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            
         // 按指定模式在字符串查找
            String tempString = null;
            String pattern = "(#)(.*)";
            
         // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	// 现在创建 matcher 对象
                Matcher m = r.matcher(tempString);
                
                if (m.find( )) {
                	//为版本信息不做处理
                 } else {
                    //日志信息存入中间向量
                	 
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
