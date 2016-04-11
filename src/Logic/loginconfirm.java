package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import Gloable.globleStatus;
import UI.LoginDesign;
import UI.MyDialog;

public class loginconfirm {
    public loginconfirm(String info)
    {
	    String fileContent = "";     

	    StringBuffer content = new StringBuffer();
	    try   
	    {       
	        File f = new File("login\\logininfomation");      
	        if(f.isFile()&&f.exists())  
	             {
	                     InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");       
	                     BufferedReader reader=new BufferedReader(read,5*1024*1024);       
	                     String line;       
	                     while ((line = reader.readLine()) != null) 
	                     {
	             	        if(info.equals(line))
	            	        {
	             	        	globleStatus.login = true;
	             	        	break;
	            	       }
	                     }
	                     fileContent = content.toString();
	        //System.out.println(fileContent);
	                     read.close();
	                     }
	        } catch (Exception e)
	    {
	        	e.printStackTrace();
	        	}    
	      if(globleStatus.login)
	      {
	    	  LoginDesign.closelogin();
		        MyDialog.createAndShowGUI();//创建UI界面	
	      }
	      else
	    	  LoginDesign.shorerror();
    }
}
