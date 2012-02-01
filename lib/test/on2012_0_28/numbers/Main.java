package on2012_0_28.numbers;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_28.numbers.Numbers",
			"SINGLE",
			"2 10 2/__2 3/__1 2 3 4/__1 2 3 4;;3;;true::2 10 2/__2 3/__4 3 1 2/__1 3 2 4;;2;;true::56 789 3/__7 2 14/__2 3 4 5/__2 3 4 5/__5 4 3 2;;8;;true::100000 1 1/__1/__1 2 3 4;;199998/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
