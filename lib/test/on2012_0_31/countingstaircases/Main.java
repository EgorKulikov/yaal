package on2012_0_31.countingstaircases;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_31.countingstaircases.CountingStaircases",
			"MULTI_NUMBER",
			"2/__3/__1 1 1/__1 1 1/__1 1 1/__2/__1 0/__1 1/__;;5/__1/__;;true::1/__3/__1 1 1/__1 1 1/__0 1 1/__;;3;;true::1/__4/__0 1 1 1/__1 1 1 1/__1 1 1 1/__1 1 1 0/__;;9/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
