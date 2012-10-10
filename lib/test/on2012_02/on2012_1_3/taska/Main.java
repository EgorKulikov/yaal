package on2012_02.on2012_1_3.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_3.taska.TaskA",
			"SINGLE",
			"1/__2/__3/__4/__12/__;;12/__;;true::2/__3/__4/__5/__24/__;;17/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
