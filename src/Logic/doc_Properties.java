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
            //in = Properties.class.getResourceAsStream("AttackRules.properties");//存放配置文件路径为class所在目录下
			prop.load(doc_Properties.class.getResourceAsStream("AttackRules.properties"));
			Enumeration enum1 = prop.propertyNames();//得到配置文件的名字
			while(enum1.hasMoreElements()) {
				String strKey = (String) enum1.nextElement();
				//System.out.println(strKey);
				//byte[] result = prop.getProperty(strKey).getBytes();
				//byte[] decryResult = doc_Properties.decrypt(result, password);
			    globleStatus.attack_regex[count] = prop.getProperty(strKey);
			    //System.out.println("解密后："+new String(decryResult));
			    //System.out.println(globleStatus.attack_regex[count]);
			    count++;
			    }
        	} catch (Exception e) {
        		e.printStackTrace();  
        		}
		}
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
		}
}


/******
public DES() {
}
//测试
public static void main(String args[]) {
//待加密内容
String str = "( \\s|\\S)*(exec(\\s|\\+)+(s|x)p\\w+)(\\s|\\S)*";
//密码，长度要是8的倍数
String password = "9588028820109132570743325311898426347863298773549468758875018339537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";

byte[] result = DES.encrypt(str.getBytes(),password);
System.out.println("加密后："+new String(result));

//直接将如上内容解密
try {
byte[] decryResult = DES.decrypt(result, password);
System.out.println("解密后："+new String(decryResult));
} catch (Exception e1) {
e1.printStackTrace();
}

}

/**
* 加密
* @param datasource byte[]
* @param password String
* @return byte[]

public static byte[] encrypt(byte[] datasource, String password) { 
try{
SecureRandom random = new SecureRandom();
DESKeySpec desKey = new DESKeySpec(password.getBytes());
//创建一个密匙工厂，然后用它把DESKeySpec转换成
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
SecretKey securekey = keyFactory.generateSecret(desKey);
//Cipher对象实际完成加密操作
Cipher cipher = Cipher.getInstance("DES");
//用密匙初始化Cipher对象
cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
//现在，获取数据并加密
//正式执行加密操作
return cipher.doFinal(datasource);
}catch(Throwable e){
e.printStackTrace();
}
return null;
}
/**
* 解密
* @param src byte[]
* @param password String
* @return byte[]
* @throws Exception

public static byte[] decrypt(byte[] src, String password) throws Exception {
// DES算法要求有一个可信任的随机数源
SecureRandom random = new SecureRandom();
// 创建一个DESKeySpec对象
DESKeySpec desKey = new DESKeySpec(password.getBytes());
// 创建一个密匙工厂
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
// 将DESKeySpec对象转换成SecretKey对象
SecretKey securekey = keyFactory.generateSecret(desKey);
// Cipher对象实际完成解密操作
Cipher cipher = Cipher.getInstance("DES");
// 用密匙初始化Cipher对象
cipher.init(Cipher.DECRYPT_MODE, securekey, random);
// 真正开始解密操作
return cipher.doFinal(src);
}
*******/
