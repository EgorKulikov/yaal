package on2012_3_14.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_14.taskb.TaskB",
			"MULTI_NUMBER",
			"4/__3 1 5 15 13 11/__3 0 8 23 22 21/__2 1 1 8 0/__6 2 8 29 20 8 18 18 21;;Case #1/: 3/__Case #2/: 2/__Case #3/: 1/__Case #4/: 3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
