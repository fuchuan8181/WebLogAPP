package gloable;

public class globleStatus {
   private static String filename;
   public static int fileType;
   public static String[] attack_regex = new String[3];
   //0 Ϊsqlע��
   //1Ϊxss��վ
   //2Ϊexec_command��ִ��Ŀ¼
   
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
