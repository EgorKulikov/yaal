package on2012_06.on2012_5_17.giftsatolympics;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_17.giftsatolympics.GiftsAtOlympics",
			"SINGLE",
			"3/__5 3 12/__4/__4/__5/__2/__12;;30/__10/__2320/__3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
