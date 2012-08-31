package on2012_02.on2012_1_11.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_11.taskc.TaskC",
			"SINGLE",
			"3/__1 2 1/__;;2/__1 2 /__1/__3 /__;;true::5/__2 3 3 1 1/__;;3/__4 1 3 /__2/__5 2 /__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
