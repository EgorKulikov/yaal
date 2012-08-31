package on2012_03.on2012_2_25.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_25.taskd.TaskD",
			"SINGLE",
			"3/__10 1/__30 2/__20 3/__2/__20 1/__20 2/__;;30/__2/__2 3/__1 1/__;;true::3/__10 4/__20 5/__30 6/__2/__70 4/__50 5/__;;50/__2/__2 3/__1 2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
