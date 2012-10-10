package on2012_03.on2012_2_31.luckyonmars;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_31.luckyonmars.LuckyOnMars",
			"MULTI_NUMBER",
			"2/__4/__4 1 2 3/__9/__8 6 9 3 4 7 1 2 5/__;;2/__7 5/__;;true::1/__3/__1 2 3/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
