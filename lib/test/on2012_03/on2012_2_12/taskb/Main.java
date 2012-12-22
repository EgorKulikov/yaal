package on2012_03.on2012_2_12.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_12.taskb.TaskB",
			"SINGLE",
			"3 2/__2 1/__3 2/__3 1/__;;5.5/__2 1 2/__1 3/__;;true::4 3/__4 1/__1 2/__2 2/__3 2/__;;8.0/__1 1/__2 4 2/__1 3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
