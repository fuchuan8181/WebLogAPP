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
        
		try {
            in = Properties.class.getResourceAsStream("AttackRules.properties");//存放配置文件路径为class所在目录下
			prop.load(in);
            Set keyValue = prop.keySet();
            for (Iterator it = keyValue.iterator(); it.hasNext();) {
                String key = (String) it.next();
                if (key.equals("AttackRules.sqli.regex")) {
                	globleStatus.attack_regex[0] = (String)prop.get(key);
                } else if (key.equals("AttackRules.xss.regex")) {
					globleStatus.attack_regex[1] = (String) prop.get(key);
				} else if (key.equals("AttackRules.exec_command.regex")) {
					globleStatus.attack_regex[2] = (String) prop.get(key);
				}
                //else if ...
            }
        } catch (Exception e) {
            //...
        }
	}
}
