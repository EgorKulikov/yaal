package on2012_0_28.bytelandice;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_28.bytelandice.BytelandIce",
			"SINGLE",
			"1 300 400 500/__1 1;;400/__;;true::2 6 8 9/__1 0/__0 2;;14;;true::3 24 32 42/__0 2/__3 1/__1 1;;116;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
