package gloable;

public class globleStatus {
	
	public static String[] sql_url;
	public static String[] xss_url;
	public static String[] exec_url;
	
	public int date_num;//����ʱ��
	public int time_num;//����ʱ��
	public int server_ip_num;//������ip
	public int request_method_num;//����ʽ
	public int url_stem_num;//�����url�������ʵ���Դ
	public int url_query_num;
	public int server_port_num;//�������˿ڣ� ������ṩ����Ĵ����˿�
	public int client_ip_num;//�ͻ���ip
	public int User_Agent_num;//�ͻ������õ�������汾��Ϣ
	public int status_num;//��Ϊִ�к�ķ���״̬
	
   private static String filename;
   public static int fileType;
   public static int regex_num = 10;
   public static String[] attack_regex = new String[regex_num];
   //0 Ϊsqlע��
   //1 Ϊxss��վ
   //2 Ϊexec_command��ִ��Ŀ¼
   
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
