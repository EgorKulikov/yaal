package on2011_11_16.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_16.taskb.TaskB",
			"SINGLE",
			"3/__3 1 2/__;;0/__;;true::2/__2 2/__;;1/__;;true::5/__5 3 3 3 1/__;;2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
