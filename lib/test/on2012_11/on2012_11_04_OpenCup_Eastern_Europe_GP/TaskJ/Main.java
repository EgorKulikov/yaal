package on2012_11.on2012_11_04_OpenCup_Eastern_Europe_GP.TaskJ;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2012_11/on2012_11_04_OpenCup_Eastern_Europe_GP/TaskJ/TaskJ.task"))
			Assert.fail();
	}
}
