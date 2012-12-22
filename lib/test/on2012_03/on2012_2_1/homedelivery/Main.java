package on2012_03.on2012_2_1.homedelivery;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_1.homedelivery.HomeDelivery",
			"MULTI_NUMBER",
			"1/__4 2/__0 1/__1 2/__3/__0 2/__0 3/__2 1/__;;YO/__NO/__YO/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
