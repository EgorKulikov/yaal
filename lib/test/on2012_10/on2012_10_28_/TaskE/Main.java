package on2012_10.on2012_10_28_.TaskE;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2012_10/on2012_10_28_/TaskE/TaskE.task"))
			Assert.fail();
	}
}
