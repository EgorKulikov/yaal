package on2012_2_12.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_12.taskd.TaskD",
			"SINGLE",
			"5 2/__1 2/__2 3/__3 4/__2 5/__;;4/__;;true::5 3/__1 2/__2 3/__3 4/__4 5/__;;2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
