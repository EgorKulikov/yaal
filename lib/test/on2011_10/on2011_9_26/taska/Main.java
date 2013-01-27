package on2011_10.on2011_9_26.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_26.taska.TaskA",
			"SINGLE",
			"98 106 214;;214::100 100 100;;100::93 500 1000;;Error"))
		{
			Assert.fail();
		}
	}
}
