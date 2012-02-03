package on2012_1_3.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_3.taskc.TaskC",
			"SINGLE",
			"10 2 3/__;;5 1 3 6 16 35 46 4 200 99;;true::5 0 0/__;;10 10 6 6 5;;true::100 15 15/__;;;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
