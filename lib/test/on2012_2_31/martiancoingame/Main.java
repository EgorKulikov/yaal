package on2012_2_31.martiancoingame;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_31.martiancoingame.MartianCoinGame",
			"SINGLE",
			"10/__1 1 1 1 1 0 0 0 0 0/__5/__0 1 7/__2 3 5/__0 1 7/__1 1 10/__0 1 7/__;;5 0 2 0/__2 3 2 0/__2 0 2 3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
