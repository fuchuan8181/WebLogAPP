package Logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xpath.internal.operations.String;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.globleStatus;

public class judgement {
	
	public static Integer num_sql = 0 ;
	public static Integer num_xss = 0 ;
	public static Integer num_exec = 0 ;

	public void Attackjudgment()
	{
		int k=0;
		MiddleDataVector vector=MiddleDataVector.getInstance();
		   statistics sta = new statistics();
		for(int i = 0;i < vector.size();i ++)
		{
			LogDataItem v=(LogDataItem) vector.m_element.get(i);
			for(int j = 0;j < 3;j ++)
			{
	       Pattern r = Pattern.compile(globleStatus.attack_regex[j]);
	       Matcher m = r.matcher(v.url_query);
	       if(m.find())
	       {
	    	   switch(j){
	    	   case 1: 
	    	   {
	    		   v.bSQL = true;
	    		   sta.statistic(statistics.sql_url, v.url_stem);//½«URL´æÈëmapÖÐ
	    		   sta.statistic(statistics.sql_arr, v.address_city);
	    		   num_sql++;
	    		   break;
	    	   }
	    	   case 2:  
	    	   {
	    		   v.bXSS = true;
	    		   sta.statistic(statistics.xss_url, v.url_stem);
	    		   sta.statistic(statistics.xss_arr, v.address_city);
	    		   num_xss ++;
	    		   break;
	    	   }
	    	   case 0:
	    	   {
	    		   v.bEXEC = true;
	    		   sta.statistic(statistics.exec_url, v.url_stem);
	    		   sta.statistic(statistics.exec_arr, v.address_city);
	    		   num_exec++;
	    		   break;
	    	   }
	    	   default: //vector.
	    	   }
	    	   break;
	       }
			}
			//System.out.println(v.bSQL);
			//System.out.println(v.url_query);
	     }
		


	}

}
