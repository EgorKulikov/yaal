package on2011_9_29.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_9_29.taskf.TaskF",
			"SINGLE",
			"4 3 1/__0 10/__0 10/__1 10/__employee 2 15 1/__employee 3 5 1/__department 0 10 1;;2/__11/__12/__11"))
		{
			Assert.fail();
		}
	}
}
