package on2012_01.on2012_0_28.vugluscr;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.vugluscr.Vugluscr",
			"SINGLE",
			"6 10/__..2......./__........../__..6.**..../__.8**...1../__..7..9..../__..........;;25;;true::2 2/__22/__11;;6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
