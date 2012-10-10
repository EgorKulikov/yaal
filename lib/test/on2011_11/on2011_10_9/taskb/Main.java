package on2011_11.on2011_10_9.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_9.taskb.TaskB",
			"SINGLE",
			"fixprefixsuffix/__;;fix;;true::abcdabc/__;;Just a legend;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
