package on2011_12.on2011_11_26.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_26.taska.TaskA",
			"SINGLE",
			"5/__9 2 7 1 2/__;;1 2 2 7 9/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
