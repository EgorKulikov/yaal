package on2012_06.on2012_5_13.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_13.taskc.TaskC",
			"MULTI_NUMBER",
			"3/__32 5 2/__5 0/__10 2/__10 10 1/__10 10/__10 1 1/__1 5;;Case #1/: 3/__Case #2/: 0/__Case #3/: 8;;true::2/__100 1 2/__2 0/__3 1000/__100 10 3/__1 2/__2 100/__3 1000/__;;Case #1/: 33/__Case #2/: 46;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
