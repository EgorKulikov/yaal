package on2012_04.on2012_3_3.luckyarray;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_3.luckyarray.LuckyArray",
			"MULTI_NUMBER",
			"2/__2 4 7/__0/__3 7 4/__0 1/__;;-1/__1 7 7/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
