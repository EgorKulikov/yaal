package on2012_07.on2012_6_23.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_23.taskc.TaskC",
			"SINGLE",
			"BABBBABBA/__;;2/__;;true::ABABB/__;;2/__;;true::ABABAB/__;;4/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
