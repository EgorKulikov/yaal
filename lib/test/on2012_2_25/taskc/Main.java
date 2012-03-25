package on2012_2_25.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_25.taskc.TaskC",
			"SINGLE",
			"3 10/__10 20 30/__;;1/__;;true::3 4/__1 2 3/__;;4/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
