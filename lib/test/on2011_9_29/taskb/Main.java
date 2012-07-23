package on2011_9_29.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_9_29.taskb.TaskB",
			"SINGLE",
			"4 5/__1 4 10 10 90 20/__1 2 5 5 50 5/__2 4 15 10 50 5/__2 3 14 1 10 1/__3 4 15 1 50 1;;27.500::2 1/__2 1 10 10 90 20;;Fail"))
		{
			Assert.fail();
		}
	}
}
