package Gloable;

import java.util.LinkedList;
import java.util.List;

public class keylist {
	public List<LogDataItem> m_element;

	private static keylist m_instance;//这是MiddleDataVector类型的实例
	public static keylist getInstance()
	{
		if (m_instance==null)
			m_instance=new keylist();
		return m_instance; 
	}
	
	public void  Clear() {
		m_element.clear();
	}
	
	private keylist()
	{
		m_element=new LinkedList<LogDataItem>();
	}
	
	public void addElement(LogDataItem x)
	{
		m_element.add(x);
	}

	public int size() {
		// TODO Auto-generated method stub
		return m_element.size();
	}
}
