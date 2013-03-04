package on2012_02.on2012_1_22.nearcows;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_22.nearcows.NearCows",
			"SINGLE",
			"6 2/__5 1/__3 6/__2 4/__2 1/__3 2/__1/__2/__3/__4/__5/__6;;15/__21/__16/__10/__8/__11;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
