package on2012_04.on2012_3_1.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_1.taska.TaskA",
			"SINGLE",
			"3 14/__;;44/__;;true::27 12/__;;48/__;;true::100 200/__;;102/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
