package on2012_07.on2012_6_23.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_23.taskb.TaskB",
			"SINGLE",
			"4/__2S 2S 2C 2C/__;;YES/__;;true::2/__3S 2C/__;;NO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
