package on2011_10.on2011_9_27.taskc1;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taskc1.TaskC",
			"SINGLE",
			"7 4/__;;1/__::4 7/__;;1/__"))
		{
			Assert.fail();
		}
	}
}
