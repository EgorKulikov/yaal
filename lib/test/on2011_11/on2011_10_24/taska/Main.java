package on2011_11.on2011_10_24.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_24.taska.TaskA",
			"SINGLE",
			"6 3/__1 2 3/__;;1 1 1/__;;true::500 100/__50 10 1/__;;1 39 60/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
