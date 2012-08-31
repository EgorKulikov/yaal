package on2012_05.on2012_4_6.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_6.taskb.TaskB",
			"SINGLE",
			"3/__1 1 1/__;;1.0 1.0 1.0/__;;true::3/__2 0 0/__;;3.0 0.0 0.0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
