package on2012_2_31.interestingmartiantrips;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_2_31.interestingmartiantrips.InterestingMartianTrips",
			"SINGLE",
			"2 2 /__5 2 /__4 3 ;;10;;true::3 2 /__2 2 /__1 3 /__1 1 ;;7;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
