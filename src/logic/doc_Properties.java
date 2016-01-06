package logic;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import gloable.globleStatus;

public class doc_Properties {
	public void get_Properties(){
		Properties prop = new Properties();
        InputStream in;
        String a ;
        
		try {
            //in = Properties.class.getResourceAsStream("AttackRules.properties");//存放配置文件路径为class所在目录下
			prop.load(doc_Properties.class.getResourceAsStream("AttackRules.properties"));
			
			a=prop.getProperty("AttackRules.sqli.regex");
			globleStatus.attack_regex[0] =a;
			/****
			 * 注释掉的不能用。。
			 * globleStatus.attack_regex[0] = prop.getProperty("AttackRules.sqli.regex");
			globleStatus.attack_regex[1] = prop.getProperty("AttackRules.xss.regex");
			globleStatus.attack_regex[2] = prop.getProperty("AttackRules.exec_command.regex");

			
			
			System.out.println(globleStatus.attack_regex[1]);
			System.out.println(globleStatus.attack_regex[2]);
						****/
			System.out.println(a);
			System.out.println(globleStatus.attack_regex[0]);
			/***
			 * 
            Set keyValue = prop.keySet();
            for (Iterator it = keyValue.iterator(); it.hasNext();) {
                String key = (String) it.next();
                if (key.equals("AttackRules.sqli.regex")) {
                	globleStatus.attack_regex[0] = (String)prop.get(key);
                	a = (String)prop.get(key);
                	System.out.println(a);
                } else if (key.equals("AttackRules.xss.regex")) {
					globleStatus.attack_regex[1] = (String) prop.get(key);
					System.out.println(globleStatus.attack_regex[1]);
				} else if (key.equals("AttackRules.exec_command.regex")) {
					globleStatus.attack_regex[2] = (String) prop.get(key);
					System.out.println(globleStatus.attack_regex[2]);
				}
                //else if ...
            }
            ***/
        } catch (Exception e) {
        	e.printStackTrace();  
        }
	}
}
