package on2012_03.on2012_2_1.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_1.taskd.TaskD",
			"SINGLE",
			"2 0 1000000000/__;;1/__;;true::3 0 100/__;;3/__;;true::4 1 1000000000/__1 4/__;;8/__;;true::6 2 1000000/__1 2/__3 4/__;;144/__;;true::3 2 100/__1 2/__1 3/__;;1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
