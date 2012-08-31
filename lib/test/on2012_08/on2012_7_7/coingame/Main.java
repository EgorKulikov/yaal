package on2012_08.on2012_7_7.coingame;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_7.coingame.CoinGame",
			"SINGLE",
			"3/__3 1 2;;16;;true::2/__2 1;;-1/__;;true::7/__1 2 3 4 5 6 7/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
