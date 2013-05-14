package on2012_02.on2012_1_18.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_18.taska.TaskA",
			"SINGLE",
			"6/__;;2/__;;true::30/__;;1/__15/__;;true::1/__;;1/__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
