package on2012_01.on2012_0_16.loyal;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_16.loyal.Loyal",
			"MULTI_NUMBER",
			"3/__1 3/__3 4/__3 16;;1/__1/__5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
