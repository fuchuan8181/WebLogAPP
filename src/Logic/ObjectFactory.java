package Logic;

public class ObjectFactory {

	public static LogReadIFace getLogFileOperationInstance(int fileType)
	{
		switch (fileType)
		{
		case 0://IIS
			return new IISLog();
		case 1://IIS
			return new ApacheLog();
		case 2:
			return new Nginxlog();
		case 3:
			return new Tomcatlog();
		}
		
	   return null;
	}
}
