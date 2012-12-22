package on2011_11.on2011_10_19.taskd0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_19.taskd0.TaskD",
			"SINGLE",
			"4 4/__1 2 3/__2 3 4/__1 3 5/__1 4 1;;2 4;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
