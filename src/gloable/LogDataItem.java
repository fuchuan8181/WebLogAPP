package gloable;

public class LogDataItem {
	
	public boolean bSQL=false;
    public boolean bXSS=false;
    public boolean bEXEC = false;
    public boolean bZK=false;
    
	public String date;//����ʱ��
	public String server_ip;//������ip
	public String request_method;//����ʽ
	public String url_request;//�����url�������ʵ���Դ
	public String server_port;//�������˿ڣ� ������ṩ����Ĵ����˿�
	public String client_ip;//�ͻ���ip
	public String User_Agent;//�ͻ������õ�������汾��Ϣ
	public String status;//��Ϊִ�к�ķ���״̬
	
	//��ר�б�ʶ������identd��ֱ�����������������ߵ�EMAIL������Ψһ��ʾ��
	public String isIdentityCheck;//apache��־��ʽ
	public String http_protocol;//apache and Nginx ��ʾhttpЭ�鼰�汾��Ϣ
	public String server_package;//apache:�����ֽ���; Nginx :���ͻ��˷��͵��ļ��������ݴ�С
	
	public String package_sent;//tomcat��־��ʽר��
	public String package_sent_excludingHTTPheaders;//tomcat
	public String server_name;//tomcat
	//��Nginx�����ڼ�¼Զ�̿ͻ��˵��û����ƣ�һ��Ϊ��-����
	public String identity_name;//tomcat�ٷ�˵always return '-' �ٷ����ͣ�Remote logical username from identified
	public String querystring;//tomcat���ʵ���aaa.jsp?bbb=ccc���������ʾ?bbb=ccc
	public String session_ID;//tomcat
	public String identified_name;//tomcat�õ�����֤�ķ����ߣ��������"-"
	public String domain_name;//tomcat
	public String time_request_inMillis;//tomcat���ʷ�����ʱ�䣬�Ժ����
	
	public String http_referer; //Nginx�п��Լ�¼�û��Ǵ��ĸ����ӷ��ʹ�����
	
	public int date_num;//����ʱ��
	public int time_num;//����ʱ��
	public int server_ip_num;//������ip
	public int request_method_num;//����ʽ
	public int url_query_request_num;//�����url�������ʵ���Դ
	public int url_query_query_num;
	public int server_port_num;//�������˿ڣ� ������ṩ����Ĵ����˿�
	public int client_ip_num;//�ͻ���ip
	public int User_Agent_num;//�ͻ������õ�������汾��Ϣ
	public int status_num;//��Ϊִ�к�ķ���״̬
}
