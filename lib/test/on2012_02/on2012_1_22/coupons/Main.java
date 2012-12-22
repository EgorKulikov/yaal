package on2012_02.on2012_1_22.coupons;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_22.coupons.Coupons",
			"SINGLE",
			"4 1 7/__3 2/__2 2/__8 1/__4 3;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
