package on2011_11.on2011_10_4.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_4.taskd.TaskD",
			"MULTI_EOF",
			"43/__1 3/__2 4/__40/__5 9/__5 12/__0;;13 1/__failed;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
