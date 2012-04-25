package on2012_3_25.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_25.taskb.TaskB",
			"SINGLE",
			"4/__2/__2 2/__;;2/__;;true::5/__3/__3 4 3/__;;3/__;;true::31/__12/__31 28 31 30 31 30 31 31 30 31 30 31/__;;7/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
