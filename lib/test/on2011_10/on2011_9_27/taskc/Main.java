package on2011_10.on2011_9_27.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taskc.TaskC",
			"MULTI_NUMBER",
			"4/__0 1/__1 1/__3 2/__7 7;;a/__b/__a/__a"))
		{
			Assert.fail();
		}
	}
}
