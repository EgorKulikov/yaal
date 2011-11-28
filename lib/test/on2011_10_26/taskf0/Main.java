package on2011_10_26.taskf0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_26.taskf0.TaskF",
			"MULTI_NUMBER",
			"3/__2 2/__2 0/__0 2/__1 4/__2/__1 0 1 1/__2 4/__3 1/__0 2 1 2;;3/__3/__5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
