package on2011_10_28.task1878;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_28.task1878.Task1878",
			"SINGLE",
			"2 1 2 3/__1 1 2 2/__4 4 3 3/__1 4 3 4/__;;1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
