package Gloable;

public class LogDataItem {
	
	public boolean bSQL=false;//SQLע�빥��
    public boolean bXSS=false;//XSS��վ����
    public boolean bEXEC = false;//��ִ�������
    public boolean bZK=false;//ײ�⹥��
    
    
	public String date;//����ʱ��
	public String server_ip;//������ip
	public String request_method;//����ʽ
	public String url_stem;//�����url�������ʵ���Դ
	public String url_query;//url�ύ�Ĳ���
	public String server_port;//�������˿ڣ� ������ṩ����Ĵ����˿�
	public String client_ip;//�ͻ���ip
	public String User_Agent;//�ͻ������õ�������汾��Ϣ
	public String status;//��Ϊִ�к�ķ���״̬
	public String address_full;
	public String address_city;
	public String address_ips;
	
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
	
}
