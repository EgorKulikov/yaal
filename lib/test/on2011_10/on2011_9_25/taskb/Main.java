package on2011_10.on2011_9_25.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_25.taskb.TaskB",
			"SINGLE",
			"5 3 2;;aa::5 3 10;;aab"))
		{
			Assert.fail();
		}
	}
}
