package on2012_03.on2012_2_12.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_12.taska.TaskA",
			"SINGLE",
			"5 3 0 0/__1 2 3 3 4/__1 3 5/__;;2/__1 1/__3 2/__;;true::3 3 2 2/__1 5 9/__3 5 7/__;;3/__1 1/__2 2/__3 3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
