package on2012_02.on2012_1_12.task2;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_12.task2.Task2",
			"MULTI_NUMBER",
			"2/__6 3/__15/__1 2/__2 2/__3 1/__4 2/__5 3/__6 2/__2 1/__2 3/__1 1/__5 2/__3 3/__4 1/__1 3/__4 3/__6 1/__5 5/__20/__1 4/__2 2/__3 3/__4 1/__5 4/__2 1/__5 3/__3 2/__1 3/__4 5/__1 2/__3 4/__3 5/__3 1/__5 1/__4 3/__2 4/__5 5/__1 5/__2 3/__;;9/__5/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
