package on2012_08.on2012_7_14.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_14.taskc.TaskC",
			"SINGLE",
			"4 3 2/__;;4/__1 1 4 5;;true::3 3 1/__;;3/__1 3 5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
