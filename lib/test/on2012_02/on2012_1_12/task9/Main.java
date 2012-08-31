package on2012_02.on2012_1_12.task9;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_12.task9.Task9",
			"SINGLE",
			"6 6 1/__1 2 1/__1 3 1/__2 4 2/__3 4 3/__4 5 5/__4 6 4/__2/__5 10/__6 20;;220/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
