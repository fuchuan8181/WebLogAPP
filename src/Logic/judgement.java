package Logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.globleStatus;

public class judgement {
	
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
	    		   sta.statistic(statistics.sql_url, v.url_stem);
	    		   sta.statistic(statistics.sql_arr, v.address_city);
	    		   break;
	    	   }
	    	   case 2:  
	    	   {
	    		   v.bXSS = true;
	    		   sta.statistic(statistics.xss_url, v.url_stem);
	    		   sta.statistic(statistics.xss_arr, v.address_city);
	    		   break;
	    	   }
	    	   case 0:
	    	   {
	    		   v.bEXEC = true;
	    		   sta.statistic(statistics.exec_url, v.url_stem);
	    		   sta.statistic(statistics.exec_arr, v.address_city);
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
