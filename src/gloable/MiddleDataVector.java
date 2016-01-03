package gloable;

import java.util.Vector;
 

public class MiddleDataVector {
	public Vector m_element;

	private static MiddleDataVector m_instance;//看不懂这是干啥的
	public static MiddleDataVector getInstance()
	{
		if (m_instance==null)
			m_instance=new MiddleDataVector();
		return m_instance; 
	}
	
	
	private MiddleDataVector()
	{
		m_element=new Vector();
	}
	
	public void addElement(LogDataItem x)
	{
		m_element.addElement(x);
	}
	

}
