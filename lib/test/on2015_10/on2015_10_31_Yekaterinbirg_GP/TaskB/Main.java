package on2015_10.on2015_10_31_Yekaterinbirg_GP.TaskB;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_10/on2015_10_31_Yekaterinbirg_GP/TaskB/TaskB.task"))
			Assert.fail();
	}
}
