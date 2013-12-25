package on2011_11.on2011_10_29.task1794;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_29.task1794.Task1794",
			"SINGLE",
			"4/__4 1 2 3/__;;2/__;;true::3/__1 1 1/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
