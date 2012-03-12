package on2012_2_12.skyscraper;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_12.skyscraper.SkyScraper",
			"SINGLE",
			"4 10/__5/__6/__3/__7;;3/__2 3 4/__1 2/__1 1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
