package on2012_07.on2012_6_31.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_31.taskb.TaskB",
			"SINGLE",
			"1/__0 0 0 0 0 0 0 0 0 1/__;;1/__;;true::2/__1 1 0 0 0 0 0 0 0 0/__;;1/__;;true::3/__1 1 0 0 0 0 0 0 0 0/__;;36/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
