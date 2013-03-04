package on2011_11.on2011_10_28.task1880;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.task1880.Task1880",
			"SINGLE",
			"5/__13 20 22 43 146/__4/__13 22 43 146/__5/__13 43 67 89 146/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
