package gloable;

public class LogDataItem {
    public boolean bSQL=false;
    public boolean bZHAN=false;
    public boolean bZK=false;
    
	public static String data;//日期时间
	public static String server_ip;//服务器ip
	public static String request_method;//请求方式
	public static String uri_request;//请求的url，被访问的资源
	public static int server_port;//服务器端口： 服务端提供服务的传输层端口
	public static String client_ip;//客户端ip
	public static String User_Agent;//客户端所用的浏览器版本信息
	public static int status;//行为执行后的返回状态
}
