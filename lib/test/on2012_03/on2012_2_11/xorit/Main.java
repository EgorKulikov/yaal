package on2012_03.on2012_2_11.xorit;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_11.xorit.XorIt",
			"SINGLE",
			"4 5/__1 1 3 4/__;;0 2 2 5 5/__;;true::2 1/__0 1073741824/__;;1073741824/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
