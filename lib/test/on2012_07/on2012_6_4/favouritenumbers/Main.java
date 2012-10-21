package on2012_07.on2012_6_4.favouritenumbers;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_4.favouritenumbers.FavouriteNumbers",
			"SINGLE",
			"1 1000000000 4 2/__62/__63/__;;163/__;;true::1 1 1 1/__2/__;;no such number/__;;true::1 1000 15 2/__6/__22/__;;67/__;;true::2 2 1 1/__2/__;;2;;true::1 1000000000 34 2/__62/__63;;663/__;;true::1 1000000 10 2/__1/__10/__;;18;;true::3 1000000000000000000 123456789 8/__2/__3/__4/__5/__6/__7/__8/__9/__;;123457301;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
