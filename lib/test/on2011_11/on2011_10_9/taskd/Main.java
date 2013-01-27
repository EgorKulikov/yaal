package on2011_11.on2011_10_9.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_9.taskd.TaskD",
			"MULTI_NUMBER",
			"2/__13/__16/__;;3/__4/__;;true::5/__1/__2/__3/__4/__5/__;;1/__1/__2/__1/__2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
