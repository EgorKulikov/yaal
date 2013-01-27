package on2012_06.on2012_5_1.flyovers;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_1.flyovers.Flyovers",
			"MULTI_NUMBER",
			"4/__3 1.500 0/__2 18.92 0/__3 3.141592653 1/__1 2/__1 47 0/__;;10.500000000/__20.920000000/__11.141592653/__0.000000000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
