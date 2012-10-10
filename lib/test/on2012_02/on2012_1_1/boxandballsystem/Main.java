package on2012_02.on2012_1_1.boxandballsystem;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_1.boxandballsystem.BoxAndBallSystem",
			"MULTI_NUMBER",
			"3/__3/__5/__100/__;;1/__5/__43264/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
