package on2012_05.on2012_4_1.digitsforest;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_1.digitsforest.DigitsForest",
			"MULTI_EOF",
			"4/__1 2 1 4/__0 1 1 1/__1 0 0 1/__1 0 0 1/__1 1 1 0/__0/__;;7/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
