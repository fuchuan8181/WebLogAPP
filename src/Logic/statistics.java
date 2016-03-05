package Logic;


import java.util.HashMap;

import java.util.Map;

public class statistics {
	
		//public static List sql_arr = new ArrayList<City>();
	    public static Map<String, Integer> sql_arr = new HashMap<String, Integer>();
	    public static Map<String, Integer> xss_arr = new HashMap<String, Integer>();
	    public static Map<String, Integer> exec_arr = new HashMap<String, Integer>();
	    public static Map<String, Integer> sql_url = new HashMap<String, Integer>();
	    public static Map<String, Integer> xss_url = new HashMap<String, Integer>();
	    public static Map<String, Integer> exec_url = new HashMap<String, Integer>();
	    
		/***public static List xss_arr = new ArrayList<City>();
		public static List exec_arr = new ArrayList<City>();
		public static List struts2_arr = new ArrayList<City>();
		public static List backdoor_arr = new ArrayList<City>();
		public static List FileAccess_arr = new ArrayList<City>();
		*****/
		public void statistic(Map<String, Integer> map,String str){
			
 		   if(map.get(str) == null)
 			  map.put(str, 1);
		   else
			   map.put(str, (Integer)map.get(str) +1);
		}
		
		public static void Clear()
		{
			sql_arr.clear();
			xss_arr.clear();
			exec_arr.clear();
			sql_url.clear();
			xss_url.clear();
			exec_url.clear();
		}

}
