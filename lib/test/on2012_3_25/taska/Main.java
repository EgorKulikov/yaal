package on2012_3_25.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_25.taska.TaskA",
			"SINGLE",
			"2 4/__0 5 6 5/__3/__0 0 0 4/__1 1 4 1/__6 0 6 4/__;;19.0000000000/__;;true::5 10/__0 0 10 10/__1/__5 0 5 9/__;;-1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
