package on2012_08.on2012_7_18.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_18.taskb.TaskB",
			"SINGLE",
			"6 10/__;;2/__TBBTTB/__;;true::4 5/__;;0/__TBTB/__;;true::2 1/__;;IMPOSSIBLE/__;;true::1 1/__;;0/__T/__;;true::1 2/__;;IMPOSSIBLE;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
