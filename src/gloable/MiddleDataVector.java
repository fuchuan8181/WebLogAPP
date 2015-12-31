package gloable;

import java.util.Vector;
 

public class MiddleDataVector {
	public Vector m_element;

	private static MiddleDataVector m_instance;
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
