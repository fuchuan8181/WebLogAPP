package logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gloable.LogDataItem;
import gloable.MiddleDataVector;
import gloable.globleStatus;

public class judgement {
	
	public void Attackjudgment()
	{
		MiddleDataVector vector=MiddleDataVector.getInstance();
		for(int i = 0;i < vector.size();i ++)
		{
			LogDataItem v=(LogDataItem) vector.m_element.get(i);
			for(int j = 0;j < 3;j ++)
			{
	       Pattern r = Pattern.compile(globleStatus.attack_regex[j]);
	       Matcher m = r.matcher(v.url_request);
	       if(m.find())
	       {
	    	   switch(j){
	    	   case 0: v.bSQL = true;
	    	   case 1:  v.bXSS= true;
	    	   case 2:  v.bEXEC= true;
	    	   }
	    	   break;
	       }
			}
			System.out.println(v.bSQL);
			System.out.println(v.url_request);
	     }
	}

}
