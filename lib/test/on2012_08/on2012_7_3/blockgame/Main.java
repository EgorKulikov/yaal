package on2012_08.on2012_7_3.blockgame;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_3.blockgame.BlockGame",
			"SINGLE",
			"3/__1 2 3/__4 5 6/__7 8 9/__;;3 2 1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
