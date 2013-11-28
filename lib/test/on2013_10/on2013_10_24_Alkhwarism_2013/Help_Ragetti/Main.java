package on2013_10.on2013_10_24_Alkhwarism_2013.Help_Ragetti;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_10/on2013_10_24_Alkhwarism_2013/Help_Ragetti/Help Ragetti.task"))
			Assert.fail();
	}
}
