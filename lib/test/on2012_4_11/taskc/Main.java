package on2012_4_11.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_4_11.taskc.TaskC",
			"SINGLE",
			"6 6 3/__1 3 6/__1 2/__2 3/__4 2/__5 6/__4 5/__3 4/__1 6/__;;3/__;;true::6 5 3/__1 5 6/__1 2/__2 3/__3 4/__4 5/__6 3/__1 5/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
