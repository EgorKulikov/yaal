package on2012_08.on2012_7_17.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_17.taskc.TaskC",
			"SINGLE",
			"58.806475 -79.298387/__-10.053668 -96.270887/__14.053668 102.270887;;26;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
