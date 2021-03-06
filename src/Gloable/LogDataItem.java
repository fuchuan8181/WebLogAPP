package Gloable;

import java.util.Date;

public class LogDataItem {
	
	public boolean bSQL=false;//SQL注入攻击
    public boolean bXSS=false;//XSS跨站攻击
    public boolean bEXEC = false;//可执行命令攻击
    public boolean bZK=false;//撞库攻击
    
    
	public Date date;//日期时间
	public String server_ip;//服务器ip
	public String request_method;//请求方式
	public String url_stem;//请求的url，被访问的资源
	public String url_query;//url提交的参数
	public String server_port;//服务器端口： 服务端提供服务的传输层端口
	public String client_ip;//客户端ip
	public String User_Agent;//客户端所用的浏览器版本信息
	public String status;//行为执行后的返回状态
	public String address_full;
	public String address_city;
	public String address_ips;
	
	//↓专有标识符：由identd或直接由浏览器返回浏览者的EMAIL或其他唯一标示？
	public String isIdentityCheck;//apache日志格式
	public String http_protocol;//apache and Nginx 显示http协议及版本信息
	public String server_package;//apache:传输字节数; Nginx :给客户端发送的文件主体内容大小
	
	public String package_sent;//tomcat日志格式专有
	public String package_sent_excludingHTTPheaders;//tomcat
	public String server_name;//tomcat
	//↓Nginx中用于记录远程客户端的用户名称（一般为“-”）
	public String identity_name;//tomcat官方说always return '-' 官方解释：Remote logical username from identified
	public String querystring;//tomcat访问的是aaa.jsp?bbb=ccc，这里就显示?bbb=ccc
	public String session_ID;//tomcat
	public String identified_name;//tomcat得到了验证的访问者，否则就是"-"
	public String domain_name;//tomcat
	public String time_request_inMillis;//tomcat访问发生的时间，以毫秒记
	
	public String http_referer; //Nginx中可以记录用户是从哪个链接访问过来的
	
}
