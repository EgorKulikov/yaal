package on2016_01.on2016_01_30_World_CodeSprint.Self_Driving_Bus;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2016_01/on2016_01_30_World_CodeSprint/Self_Driving_Bus/Self-Driving Bus.task"))
			Assert.fail();
	}
}
