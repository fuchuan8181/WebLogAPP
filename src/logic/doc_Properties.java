package logic;

import java.util.Enumeration;
import java.util.Properties;
import gloable.globleStatus;

public class doc_Properties {
	public void get_Properties(){
		Properties prop = new Properties();
        int count = 0;
		try {
            //in = Properties.class.getResourceAsStream("AttackRules.properties");//��������ļ�·��Ϊclass����Ŀ¼��
			prop.load(doc_Properties.class.getResourceAsStream("AttackRules.properties"));
			Enumeration enum1 = prop.propertyNames();//�õ������ļ�������
			while(enum1.hasMoreElements()) {
				String strKey = (String) enum1.nextElement();
				System.out.println(strKey);
			    globleStatus.attack_regex[count] = prop.getProperty(strKey);
			    System.out.println(globleStatus.attack_regex[count]);
			    //System.out.println(globleStatus.attack_regex[count]);
			    count++;
			    }
        	} catch (Exception e) {
        		e.printStackTrace();  
        		}
		}
}
