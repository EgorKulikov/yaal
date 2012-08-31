package on2012_02.on2012_1_12.task4;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_12.task4.Task4",
			"SINGLE",
			"2 500009/__2 1/__2 3;;9/__6561;;true::1 3/__5 5/__;;3;;true::1 5/__5 2/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
