package on2012_01.on2012_0_7.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_7.taska.TaskA",
			"SINGLE",
			"4 10 4/__;;YES/__;;true::5 10 4/__;;NO/__;;true::1 10 10/__;;YES/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
