package gloable;

public class globleStatus {
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
