package Logic;

import Gloable.globleStatus;
import junit.framework.TestCase;

public class doc_PropertiesTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGet_Properties() {
		doc_Properties test = new doc_Properties();
		test.get_Properties();
		//System.out.println(globleStatus.attack_regex[0]);
		//System.out.println(globleStatus.attack_regex[1]);
		//System.out.println(globleStatus.attack_regex[2]);
	}
}
