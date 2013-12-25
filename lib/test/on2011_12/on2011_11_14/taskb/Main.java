package on2011_12.on2011_11_14.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_14.taskb.TaskB",
			"MULTI_EOF",
			"5 2/__1 2 3/__4 5/__/__5 2/__1 3 5/__2 4/__/__5 1/__1 2 3 4 5/__/__5 5/__1/__2/__3/__4/__5/__/__0 0;;5 2/__1 2 3 4/__5/__/__5 4/__1 4/__2/__3/__5/__/__5 2/__1 2 3 5/__4/__/__5 4/__1/__2/__3/__4 5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
