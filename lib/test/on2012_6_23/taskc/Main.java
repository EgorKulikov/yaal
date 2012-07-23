package on2012_6_23.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_6_23.taskc.TaskC",
			"SINGLE",
			"4 4/__1 2/__2 4/__1 3/__3 4/__;;1.000000000000/__;;true::11 14/__1 2/__1 3/__2 4/__3 4/__4 5/__4 6/__5 11/__6 11/__1 8/__8 9/__9 7/__11 7/__1 10/__10 4/__;;1.714285714286/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
