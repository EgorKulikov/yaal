package on2012_05.on2012_4_8.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_8.taskb.TaskB",
			"MULTI_NUMBER",
			"4/__200 1 2/__250 233/__180 100/__100 3 3/__500 500 500/__500 500 600/__500 140 1000/__10 10 10/__10 10 490/__10 10 10/__100 3 3/__500 100 500/__100 100 500/__500 500 500/__10 10 10/__10 10 10/__10 10 10/__100 2 2/__1000 1000/__1000 1000/__100 900/__900 100;;Case #1/: 11.7/__Case #2/: 3.0/__Case #3/: 18.0/__Case #4/: 0.0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
