package on2012_01.on2012_0_31.exponentiatingtoaprimepower;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_31.exponentiatingtoaprimepower.ExponentiatingToAPrimePower",
			"MULTI_NUMBER",
			"2/__2 2 1000/__2 3 /__2 3 10/__2 3 ;;25/__5/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
