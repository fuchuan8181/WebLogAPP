package gloable;

public class LogDataItem {
    public boolean bSQL=false;
    public boolean bZHAN=false;
    public boolean bZK=false;
    
	public String date;//日期时间
	public String server_ip;//服务器ip
	public String request_method;//请求方式
	public String uri_request;//请求的url，被访问的资源
	public String server_port;//服务器端口： 服务端提供服务的传输层端口
	public String client_ip;//客户端ip
	public String User_Agent;//客户端所用的浏览器版本信息
	public String status;//行为执行后的返回状态
	
	
	public int date_num;//日期时间
	public int time_num;//日期时间
	public int server_ip_num;//服务器ip
	public int request_method_num;//请求方式
	public int uri_request_num;//请求的url，被访问的资源
	public int server_port_num;//服务器端口： 服务端提供服务的传输层端口
	public int client_ip_num;//客户端ip
	public int User_Agent_num;//客户端所用的浏览器版本信息
	public int status_num;//行为执行后的返回状态
}
