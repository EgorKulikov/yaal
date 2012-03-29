package on2012_2_29.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_29.taskb.TaskB",
			"SINGLE",
			"3 1 0/__10 20 30/__-1 -1 2/__;;0.300000000000/__;;true::1 1 1/__100/__123/__;;1.000000000000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
