package approved.on2011_10_1.newrestaurant;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"approved.on2011_10_1.newrestaurant.NewRestaurant",
			"MULTI_NUMBER",
			"4/__1 1 1/__2 2 2/__4 3 2/__5 7 3;;1/__4/__45/__5887;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
