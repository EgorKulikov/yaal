package on2011_10_22.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_22.taskc.TaskC",
			"SINGLE",
			"1 4/__4 5/__B 2 16 99/__P 6 32 13/__P 2 87 4/__P 38 96 49/__1 2 4/__1 3 40/__2 3 75/__2 4 76/__3 4 77;;127;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
