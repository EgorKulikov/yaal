package on2011_11_1.cielandeggs;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_1.cielandeggs.CielAndEggs",
			"MULTI_NUMBER",
			"3/__2 1/__5 8/__2 1/__5 58/__3 2/__1 2 3/__;;8/__10/__5/__;;true::1/__5 3/__1 1 10 10 100/__;;23/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
