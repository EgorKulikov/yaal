package on2011_11.on2011_10_9.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_9.taska.TaskA",
			"SINGLE",
			"10 70 100 100 25/__;;99 33;;true::300 500 1000 1000 300/__;;1000 0;;true::143 456 110 117 273/__;;76 54;;true::1000000 1000000 1000000 1000000 1000000;;1000000 1000000;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
