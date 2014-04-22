package on2014_03.on2014_03_23_OpenCup_Kazan_GP.TaskL;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2014_03/on2014_03_23_OpenCup_Kazan_GP/TaskL/TaskL.task"))
			Assert.fail();
	}
}
