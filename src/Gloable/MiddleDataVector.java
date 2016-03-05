package Gloable;


import java.util.LinkedList;
import java.util.List;


public class MiddleDataVector {
	public List<LogDataItem> m_element;

	private static MiddleDataVector m_instance;//这是MiddleDataVector类型的实例
	public static MiddleDataVector getInstance()
	{
		if (m_instance==null)
			m_instance=new MiddleDataVector();
		return m_instance; 
	}
	
	public void  Clear() {
		m_element.clear();
	}
	
	private MiddleDataVector()
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
