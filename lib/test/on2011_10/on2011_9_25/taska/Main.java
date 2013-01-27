package on2011_10.on2011_9_25.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_25.taska.TaskA",
			"SINGLE",
			"20 46;;b::1 1;;a::1 2;;No solution"))
		{
			Assert.fail();
		}
	}
}
