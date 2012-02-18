package on2012_1_18.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_18.taskc.TaskC",
			"SINGLE",
			"3 3 10/__0 10 100/__100 0/__1 2/__2 3/__1 3/__;;90.000000000/__;;true::10 8 187/__0 10 30 70 150 310 630 1270 2550 51100/__13 87 65 0 100 44 67 3 4/__1 10/__2 9/__3 8/__1 5/__6 10/__2 7/__4 10/__4 5/__;;76859.990000000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
