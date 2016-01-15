package Gloable;


import java.util.LinkedList;
import java.util.List;


public class MiddleDataVector {
	public List<LogDataItem> m_element;

	private static MiddleDataVector m_instance;//看不懂这是干啥的
	public static MiddleDataVector getInstance()
	{
		if (m_instance==null)
			m_instance=new MiddleDataVector();
		return m_instance; 
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
