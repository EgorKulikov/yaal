package on2012_01.on2012_0_31.generatingbignumbersii;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_31.generatingbignumbersii.GeneratingBigNumbersII",
			"MULTI_NUMBER",
			"2/__2 0 2 0 0 0 0 0 /__20 20 20 20 20 20 20 20;;5/__160;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
