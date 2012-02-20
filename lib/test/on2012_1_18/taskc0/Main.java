package on2012_1_18.taskc0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_18.taskc0.TaskC",
			"SINGLE",
			"4 2 1924;;94;;true::7 3 1231234;;3234;;true::10 4 4177252841;;775841;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
