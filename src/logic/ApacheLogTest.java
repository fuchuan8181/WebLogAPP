package logic;

import junit.framework.TestCase;

public class ApacheLogTest extends TestCase {
	public ApacheLog aLog;

	protected void setUp() throws Exception {
		aLog=new ApacheLog();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testReadAndAnalysis() {
		aLog.readAndAnalysis("11111");  
	}

}
