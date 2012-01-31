package on2012_0_25.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_25.taskd.TaskD",
			"SINGLE",
			"4 6 1/__1 2 1/__1 3 3/__2 3 1/__2 4 1/__3 4 1/__1 4 2/__2/__;;3/__;;true::5 6 3/__3 1 1/__3 2 1/__3 4 1/__3 5 1/__1 2 6/__4 5 8/__4/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
