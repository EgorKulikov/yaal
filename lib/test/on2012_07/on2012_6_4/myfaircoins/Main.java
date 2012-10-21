package on2012_07.on2012_6_4.myfaircoins;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_4.myfaircoins.MyFairCoins",
			"MULTI_NUMBER",
			"3/__1/__2/__3/__;;1/__3/__8/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
