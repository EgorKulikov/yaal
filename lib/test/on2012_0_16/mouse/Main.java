package on2012_0_16.mouse;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_16.mouse.Mouse",
			"SINGLE",
			"400/__200/__3/__140 120 360 20/__100 100 160 40/__20 180 200 80/__3/__120 40/__300 180/__300 60;;2/__0/__1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
