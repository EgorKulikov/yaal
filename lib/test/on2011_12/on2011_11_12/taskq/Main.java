package on2011_12.on2011_11_12.taskq;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_12.taskq.TaskQ",
			"SINGLE",
			"3 2/__2/__1 1/__2 2/__;;* 2/__2 */__1 1/__;;true::2 2/__0/__;;0 0/__0 0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
