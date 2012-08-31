package on2012_08.on2012_7_17.divide;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_17.divide.Divide",
			"MULTI_NUMBER",
			"2/__4 6 1 7 5 2 5 1 1/__5 6 -1 4 3 -6 1 -6 -6 0 -9;;5.66/__23.827;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
