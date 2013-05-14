package on2012_01.on2012_0_28.boattrip;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.boattrip.BoatTrip",
			"SINGLE",
			"5/__2 1 3/__3 2 7/__4 3 3/__5 3 4;;26;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
