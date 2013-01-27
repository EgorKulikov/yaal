package on2012_04.on2012_3_21.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_21.taskf.TaskF",
			"SINGLE",
			"2 4 3/__1 1 1/__1 2 2/__2 1 3/__2 2 7;;2;;true::2 4 7/__1 1 1/__1 2 2/__2 1 3/__2 2 7;;8;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
