package on2012_04.on2012_3_25.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_25.taskc.TaskC",
			"SINGLE",
			"5 3/__0 -2 3 -5 1/__2/__;;10/__;;true::5 2/__1 -3 -10 4 1/__3/__;;14/__;;true::3 3/__-2 -5 4/__1/__;;11/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
