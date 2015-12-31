package gloable;

public class globleStatus {
   private static String filename;
   public static int fileType;
   
   public static int getFileType() {
	   
		return fileType;
	}

	public static void setFileType(int fileType) {
		globleStatus.fileType = fileType;
		System.out.printf("%d", globleStatus.fileType);
	}

public static String getFilename() {
	return filename;
}

public static void setFilename(String filename) {
	globleStatus.filename = filename;
	System.out.printf("%s", globleStatus.filename);
}
   
   
}
