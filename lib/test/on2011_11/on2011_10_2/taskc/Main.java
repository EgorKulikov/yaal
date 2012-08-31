package on2011_11.on2011_10_2.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_2.taskc.TaskC",
			"MULTI_EOF",
			"3x + 2y = 10/__x + y = 0/__35x + 15y = 67;;1/__1/__0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
