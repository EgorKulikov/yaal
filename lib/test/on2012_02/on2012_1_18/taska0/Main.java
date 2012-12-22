package on2012_02.on2012_1_18.taska0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_18.taska0.TaskA",
			"SINGLE",
			"3 SSS;;3;;true::4 SLLS;;4;;true::9 SLLLLSSLL;;7;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
