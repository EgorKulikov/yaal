package on2012_4_5.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_4_5.taskc.TaskC",
			"MULTI_EOF",
			"2 2 2/__4 2 2 2 2/__6 2 2 2 2 2 2/__0;;2/__12/__56;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
