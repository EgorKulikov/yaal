package on2012_01.on2012_0_28.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.taskc.TaskC",
			"SINGLE",
			"3 2/__10 10 10/__;;3/__;;true::4 2/__4 4 7 7/__;;4/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
