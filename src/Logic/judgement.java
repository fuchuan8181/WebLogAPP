package Logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.xpath.internal.operations.String;

import Gloable.LogDataItem;
import Gloable.MiddleDataVector;
import Gloable.execattacklist;
import Gloable.globleStatus;
import Gloable.sqlattacklist;
import Gloable.xssattacklist;

public class judgement {
	
	public static Integer num_sql;
	public static Integer num_xss ;
	public static Integer num_exec ;

	public void Attackjudgment()
	{
		int k=0;
		num_sql = 0;
		num_xss = 0;
		num_exec = 0;
		MiddleDataVector vector=MiddleDataVector.getInstance();
		sqlattacklist list_sql = sqlattacklist.getInstance();
		xssattacklist list_xss = xssattacklist.getInstance();
		execattacklist list_exec = execattacklist.getInstance();
		list_sql.Clear();
		list_xss.Clear();
		list_exec.Clear();
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
					 list_sql.addElement(v);
	    		   sta.statistic(statistics.sql_url, v.url_stem);//½«URL´æÈëmapÖÐ
	    		   sta.statistic(statistics.sql_arr, v.address_city);
	    		   num_sql++;
	    		   break;
	    	   }
	    	   case 2:  
	    	   {
	    		   v.bXSS = true;
					list_xss.addElement(v);
	    		   sta.statistic(statistics.xss_url, v.url_stem);
	    		   sta.statistic(statistics.xss_arr, v.address_city);
	    		   num_xss ++;
	    		   break;
	    	   }
	    	   case 0:
	    	   {
	    		   v.bEXEC = true;
					list_exec.addElement(v);
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
