package on2012_01.on2012_0_25.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_25.taska.TaskA",
			"SINGLE",
			"4/__33 44 11 22/__;;2/__;;true::7/__10 10 58 31 63 40 76/__;;10/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
