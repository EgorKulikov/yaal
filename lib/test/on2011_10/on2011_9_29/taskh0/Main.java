package on2011_10.on2011_9_29.taskh0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_29.taskh0.TaskH",
			"MULTI_NUMBER",
			"3/__180001/__12345/__615000;;Case 1/: 2000/__Case 2/: 0/__Case 3/: 50250"))
		{
			Assert.fail();
		}
	}
}
