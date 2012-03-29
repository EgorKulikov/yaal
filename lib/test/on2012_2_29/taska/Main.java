package on2012_2_29.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_29.taska.TaskA",
			"SINGLE",
			"3 10 10000/__0 10/__5 11/__1000 1/__;;1000.5000000000/__1000.5000000000/__11000.0500000000/__;;true::1 2 26/__28 29/__;;33.0990195136/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
