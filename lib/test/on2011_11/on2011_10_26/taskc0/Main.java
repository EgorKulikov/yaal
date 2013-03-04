package on2011_11.on2011_10_26.taskc0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskc0.TaskC",
			"MULTI_NUMBER",
			"2/__2/__1 2/__1 1/__5/__3 2 3 4/__1 1/__2 1 5/__1 1/__1 3;;1/__5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
