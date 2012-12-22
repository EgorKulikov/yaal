package on2011_11.on2011_10_28.taski;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.taski.TaskI",
			"SINGLE",
			"2/__;;2/__;;true::1008/__;;2^4*3^2*7/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
