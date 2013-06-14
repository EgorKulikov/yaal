package on2011_12.on2011_11_16.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_16.taskc.TaskC",
			"SINGLE",
			"5/__1 10/__2 9/__3 8/__4 7/__5 6/__;;4/__;;true::5/__1 100/__2 50/__51 99/__52 98/__10 60/__;;4/__;;true::1/__1 1000000000/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
