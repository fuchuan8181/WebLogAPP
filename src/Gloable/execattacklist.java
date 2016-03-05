package Gloable;

import java.util.LinkedList;
import java.util.List;

public class execattacklist {
	public List<LogDataItem> m_execelement;

	private static execattacklist m_execinstance;//这是MiddleDataVector类型的实例
	public static execattacklist getInstance()
	{
		if (m_execinstance==null)
			m_execinstance=new execattacklist();
		return m_execinstance; 
	}
	
	public void  Clear() {
		m_execelement.clear();
	}
	
	private execattacklist()
	{
		m_execelement=new LinkedList<LogDataItem>();
	}
	
	public void addElement(LogDataItem x)
	{
		m_execelement.add(x);
	}

	public int size() {
		// TODO Auto-generated method stub
		return m_execelement.size();
	}
}
