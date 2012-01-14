package on2011_9_27.taska0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_9_27.taska0.TaskA",
			"SINGLE",
			"2 7/__;;33/__::7 7/__;;7/__::1000000000 1000000000;;4444444444"))
		{
			Assert.fail();
		}
	}
}
