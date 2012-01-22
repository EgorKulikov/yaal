package on2012_0_9.total;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_9.total.Total",
			"SINGLE",
			"7 1/__1 2 3 4 5 6 7;;8;;true::7 2/__1 2 3 4 5 6 7;;14;;true::5 2/__5 0 10 1 6;;25;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
