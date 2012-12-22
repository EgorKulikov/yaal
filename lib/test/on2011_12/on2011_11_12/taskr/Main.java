package on2011_12.on2011_11_12.taskr;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_12.taskr.TaskR",
			"SINGLE",
			"2 1/__;;4/__;;true::14 3/__;;016/__;;true::3 3/__;;27/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
