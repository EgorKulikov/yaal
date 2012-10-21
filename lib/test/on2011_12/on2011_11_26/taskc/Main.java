package on2011_12.on2011_11_26.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_26.taskc.TaskC",
			"SINGLE",
			"1/__3/__;;1/__;;true::5/__9 15 22 15 22/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
