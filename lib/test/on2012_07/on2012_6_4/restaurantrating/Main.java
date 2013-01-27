package on2012_07.on2012_6_4.restaurantrating;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_4.restaurantrating.RestaurantRating",
			"SINGLE",
			"10/__1 1/__1 7/__2/__1 9/__1 21/__1 8/__1 5/__2/__1 9/__2/__;;No reviews yet/__9/__9/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
