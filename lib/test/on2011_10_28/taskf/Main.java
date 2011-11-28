package on2011_10_28.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_28.taskf.TaskF",
			"SINGLE",
			"3 4 5/__;;YES/__;;true::7 7 7/__;;NO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
