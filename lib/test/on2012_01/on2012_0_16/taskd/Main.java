package on2012_01.on2012_0_16.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_16.taskd.TaskD",
			"SINGLE",
			"2/__3 1/__2 1/__;;2 1/__;;true::4/__2 2/__3 1/__5 3/__4 2/__;;1 3 4 2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
