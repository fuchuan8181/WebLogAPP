package gloable;

public class LogDataItem {
    public boolean bSQL=false;
    public boolean bZHAN=false;
    public boolean bZK=false;
    
	public String date;//����ʱ��
	public String server_ip;//������ip
	public String request_method;//����ʽ
	public String uri_request;//�����url�������ʵ���Դ
	public String server_port;//�������˿ڣ� ������ṩ����Ĵ����˿�
	public String client_ip;//�ͻ���ip
	public String User_Agent;//�ͻ������õ�������汾��Ϣ
	public String status;//��Ϊִ�к�ķ���״̬
	
	public String isIdentityCheck;//apache��־��ʽר��
	public String url_request;//apache
	public String http_protocol;//apache
	public String server_package;//apache
	
	public String package_sent;//tomcat��־��ʽר��
	public String package_sent_excludingHTTPheaders;//tomcat
	public String server_name;//tomcat
	public String identity_name;//tomcat�ٷ�˵always return '-' �ٷ����ͣ�Remote logical username from identified
	public String querystring;//tomcat���ʵ���aaa.jsp?bbb=ccc���������ʾ?bbb=ccc
	public String session_ID;//tomcat
	public String identified_name;//tomcat�õ�����֤�ķ����ߣ��������"-"
	public String domain_name;//tomcat
	public String time_request_inMillis;//tomcat���ʷ�����ʱ�䣬�Ժ����
	
	public int date_num;//����ʱ��
	public int time_num;//����ʱ��
	public int server_ip_num;//������ip
	public int request_method_num;//����ʽ
	public int uri_request_num;//�����url�������ʵ���Դ
	public int server_port_num;//�������˿ڣ� ������ṩ����Ĵ����˿�
	public int client_ip_num;//�ͻ���ip
	public int User_Agent_num;//�ͻ������õ�������汾��Ϣ
	public int status_num;//��Ϊִ�к�ķ���״̬
}
