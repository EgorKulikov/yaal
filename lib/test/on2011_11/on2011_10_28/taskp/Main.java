package on2011_11.on2011_10_28.taskp;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.taskp.TaskP",
			"SINGLE",
			"1 0 5 0/__;;5/__;;true::-1 -2 2 4/__;;4/__;;true::1 1 1 1;;1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
