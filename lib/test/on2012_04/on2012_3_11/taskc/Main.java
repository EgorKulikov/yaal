package on2012_04.on2012_3_11.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_11.taskc.TaskC",
			"SINGLE",
			"3 1/__2 7 5/__1 3 3/__4 1 3/__;;0 1 1/__;;true::5 2/__1 5 4/__1 4 5/__1 3 2/__4 1 2/__5 6 1/__;;1 1 0 0 1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
