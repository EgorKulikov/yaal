package on2013_04.on2013_04_07_OpenCup_America_GP.TaskJ;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2013_04/on2013_04_07_OpenCup_America_GP/TaskJ/TaskJ.task"))
			Assert.fail();
	}
}
