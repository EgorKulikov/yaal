package on2012_01.on2012_0_19.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_19.taskc.TaskC",
			"SINGLE",
			"4.0 4.0/__4.0 5.0/__5.0 6.0/__1.0 20.0/__1.0 21.0/__1.0 22.0/__1.0 25.0/__1.0 26.0;;4;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
