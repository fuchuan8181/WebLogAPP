package gloable;

public class globleStatus {
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
