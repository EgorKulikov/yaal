package on2012_07.on2012_6_31.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_31.taska.TaskA",
			"SINGLE",
			"1/__1/__0/__;;1/__;;true::5/__2 2 1 1 3/__1 5/__2 5 1/__2 5 4/__1 5/__0/__;;7/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
