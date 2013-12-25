package on2012_05.on2012_4_1.median;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_1.median.Median",
			"MULTI_NUMBER",
			"2/__3/__1 2 3/__4/__2 1 1 2/__;;2/__1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
