package on2013_04.on2013_04_21_OpenCup_Azov_GP.TaskG;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_04/on2013_04_21_OpenCup_Azov_GP/TaskG/TaskG.task"))
			Assert.fail();
	}
}
