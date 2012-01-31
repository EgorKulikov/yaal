package on2011_10_28.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_28.taska.TaskA",
			"SINGLE",
			"4 5 6 10 20/__;;YES/__;;true::4 5 6 3 4/__;;NO/__;;true::6 5 4 4 5/__;;YES/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
