package on2011_11.on2011_10_28.taskt;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.taskt.TaskT",
			"SINGLE",
			"6 1 2 5 2 2 20/__;;4/__;;true::5 4 6 4 4 4/__;;2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
