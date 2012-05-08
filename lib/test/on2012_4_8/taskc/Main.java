package on2012_4_8.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_4_8.taskc.TaskC",
			"MULTI_NUMBER",
			"empty",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
