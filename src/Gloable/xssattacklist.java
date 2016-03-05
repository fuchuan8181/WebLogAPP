package Gloable;

import java.util.LinkedList;
import java.util.List;

public class xssattacklist {
	
	public List<LogDataItem> m_xsselement;

	private static xssattacklist m_xssinstance;//这是MiddleDataVector类型的实例
	public static xssattacklist getInstance()
	{
		if (m_xssinstance==null)
			m_xssinstance=new xssattacklist();
		return m_xssinstance; 
	}
	
	public void  Clear() {
		m_xsselement.clear();
	}
	
	private xssattacklist()
	{
		m_xsselement=new LinkedList<LogDataItem>();
	}
	
	public void addElement(LogDataItem x)
	{
		m_xsselement.add(x);
	}

	public int size() {
		// TODO Auto-generated method stub
		return m_xsselement.size();
	}

}
