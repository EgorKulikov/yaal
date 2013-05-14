package on2012_06.on2012_5_9.coolnumbers;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_9.coolnumbers.CoolNumbers",
			"MULTI_NUMBER",
			"5/__2/__999/__1459/__18898888/__99999999;;2 3/__999 1000/__1458 1460/__18895680 18900000/__99900000 100000000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
