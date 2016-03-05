package Gloable;

import java.util.LinkedList;
import java.util.List;


public class sqlattacklist {
	public List<LogDataItem> m_sqlelement;

	private static sqlattacklist m_sqlinstance;//这是MiddleDataVector类型的实例
	public static sqlattacklist getInstance()
	{
		if (m_sqlinstance==null)
			m_sqlinstance=new sqlattacklist();
		return m_sqlinstance; 
	}
	
	public void  Clear() {
		m_sqlelement.clear();
	}
	
	private sqlattacklist()
	{
		m_sqlelement=new LinkedList<LogDataItem>();
	}
	
	public void addElement(LogDataItem x)
	{
		m_sqlelement.add(x);
	}

	public int size() {
		// TODO Auto-generated method stub
		return m_sqlelement.size();
	}
}



