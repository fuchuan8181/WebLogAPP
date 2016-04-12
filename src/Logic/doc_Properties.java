package Logic;

import java.util.Enumeration;
import java.util.Properties;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import Gloable.globleStatus;

public class doc_Properties {
	public void get_Properties(){
		Properties prop = new Properties();
		String password = "9588028820109132570743325311898426347863298773549468758875018339537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";
        int count = 0;
		try {
            //in = Properties.class.getResourceAsStream("AttackRules.properties");//��������ļ�·��Ϊclass����Ŀ¼��
			prop.load(doc_Properties.class.getResourceAsStream("AttackRules.properties"));
			Enumeration enum1 = prop.propertyNames();//�õ������ļ�������
			while(enum1.hasMoreElements()) {
				String strKey = (String) enum1.nextElement();
				//System.out.println(strKey);
				//byte[] result = prop.getProperty(strKey).getBytes();
				//byte[] decryResult = doc_Properties.decrypt(result, password);
			    globleStatus.attack_regex[count] = prop.getProperty(strKey);
			    //System.out.println("���ܺ�"+new String(decryResult));
			    //System.out.println(globleStatus.attack_regex[count]);
			    count++;
			    }
        	} catch (Exception e) {
        		e.printStackTrace();  
        		}
		}
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom random = new SecureRandom();
		// ����һ��DESKeySpec����
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// ����һ���ܳ׹���
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// ��DESKeySpec����ת����SecretKey����
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance("DES");
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// ������ʼ���ܲ���
		return cipher.doFinal(src);
		}
}


/******
public DES() {
}
//����
public static void main(String args[]) {
//����������
String str = "( \\s|\\S)*(exec(\\s|\\+)+(s|x)p\\w+)(\\s|\\S)*";
//���룬����Ҫ��8�ı���
String password = "9588028820109132570743325311898426347863298773549468758875018339537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";

byte[] result = DES.encrypt(str.getBytes(),password);
System.out.println("���ܺ�"+new String(result));

//ֱ�ӽ��������ݽ���
try {
byte[] decryResult = DES.decrypt(result, password);
System.out.println("���ܺ�"+new String(decryResult));
} catch (Exception e1) {
e1.printStackTrace();
}

}

/**
* ����
* @param datasource byte[]
* @param password String
* @return byte[]

public static byte[] encrypt(byte[] datasource, String password) { 
try{
SecureRandom random = new SecureRandom();
DESKeySpec desKey = new DESKeySpec(password.getBytes());
//����һ���ܳ׹�����Ȼ��������DESKeySpecת����
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
SecretKey securekey = keyFactory.generateSecret(desKey);
//Cipher����ʵ����ɼ��ܲ���
Cipher cipher = Cipher.getInstance("DES");
//���ܳ׳�ʼ��Cipher����
cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
//���ڣ���ȡ���ݲ�����
//��ʽִ�м��ܲ���
return cipher.doFinal(datasource);
}catch(Throwable e){
e.printStackTrace();
}
return null;
}
/**
* ����
* @param src byte[]
* @param password String
* @return byte[]
* @throws Exception

public static byte[] decrypt(byte[] src, String password) throws Exception {
// DES�㷨Ҫ����һ�������ε������Դ
SecureRandom random = new SecureRandom();
// ����һ��DESKeySpec����
DESKeySpec desKey = new DESKeySpec(password.getBytes());
// ����һ���ܳ׹���
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
// ��DESKeySpec����ת����SecretKey����
SecretKey securekey = keyFactory.generateSecret(desKey);
// Cipher����ʵ����ɽ��ܲ���
Cipher cipher = Cipher.getInstance("DES");
// ���ܳ׳�ʼ��Cipher����
cipher.init(Cipher.DECRYPT_MODE, securekey, random);
// ������ʼ���ܲ���
return cipher.doFinal(src);
}
*******/
