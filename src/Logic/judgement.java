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
	    	   case 0: 
	    	   {
	    		   v.bSQL = true;
	    		   globleStatus.sql_url[k] = v.url_query;
	    		   k ++;
	    		   sta.statistic(statistics.sql_arr, v.address_city);
	    		   break;
	    	   }
	    	   case 1:  
	    	   {
	    		   v.bXSS = true;
	    		   globleStatus.xss_url[k] = v.url_query;
	    		   k ++;
	    		   sta.statistic(statistics.xss_arr, v.address_city);
	    		   break;
	    	   }
	    	   case 2:
	    	   {
	    		   v.bEXEC = true;
	    		   globleStatus.exec_url[k] = v.url_query;
	    		   k ++;
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
