package on2011_10.on2011_9_29.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_29.taskd.TaskD",
			"SINGLE",
			"4 4/__1 2/__3 1/__2 4/__3 4;;2/__1 2 2 3::3 3/__1 2/__2 3/__1 3;;-1"))
		{
			Assert.fail();
		}
	}
}
