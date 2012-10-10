package on2011_11.on2011_10_30.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_30.taskf.TaskF",
			"MULTI_NUMBER",
			"2/__5/__42/__43/__39/__93/__44/__5/__10/__10/__22/__10/__30;;110/__ERROR;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
