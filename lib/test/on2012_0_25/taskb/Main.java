package on2012_0_25.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_25.taskb.TaskB",
			"SINGLE",
			"2 5 4 2/__3/__3 1 2/__5 3 1/__1 3 2/__;;4/__;;true::5 2 6 3/__2/__6 2 2/__6 5 3/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
