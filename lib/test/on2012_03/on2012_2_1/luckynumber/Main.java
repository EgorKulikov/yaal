package on2012_03.on2012_2_1.luckynumber;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_1.luckynumber.LuckyNumber",
			"MULTI_NUMBER",
			"4/__1 100/__1 10000/__1 100000/__4444 4447/__;;0/__16/__640/__2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
