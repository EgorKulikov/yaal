package on2012_07.on2012_6_23.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_23.taskd.TaskD",
			"SINGLE",
			"3/__3 10 4/__2 4 10 15 20/__;;1 1 1 0 0 /__1/__;;true::4/__10 4 39 2/__3 5 10 11 12/__;;3 0 1 0 3 /__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
