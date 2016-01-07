package gloable;

public class globleStatus {
	
	public static String[] sql_url;
	public static String[] xss_url;
	public static String[] exec_url;
	
	public int date_num;//日期时间
	public int time_num;//日期时间
	public int server_ip_num;//服务器ip
	public int request_method_num;//请求方式
	public int url_stem_num;//请求的url，被访问的资源
	public int url_query_num;
	public int server_port_num;//服务器端口： 服务端提供服务的传输层端口
	public int client_ip_num;//客户端ip
	public int User_Agent_num;//客户端所用的浏览器版本信息
	public int status_num;//行为执行后的返回状态
	
   private static String filename;
   public static int fileType;
   public static int regex_num = 10;
   public static String[] attack_regex = new String[regex_num];
   //0 为sql注入
   //1 为xss跨站
   //2 为exec_command可执行目录
   
   public static int getFileType() {
		return fileType;
	}

	public static void setFileType(int fileType) {
		globleStatus.fileType = fileType;
	}

public static String getFilename() {
	return filename;
	}

public static void setFilename(String filename) {
	globleStatus.filename = filename;
	}  
}
