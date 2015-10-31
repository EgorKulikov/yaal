package on2015_06.on2015_06_20_IPSC.TaskD;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_06/on2015_06_20_IPSC/TaskD/TaskD.task"))
			Assert.fail();
	}
}
