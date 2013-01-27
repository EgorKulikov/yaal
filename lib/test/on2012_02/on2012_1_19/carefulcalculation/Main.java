package on2012_02.on2012_1_19.carefulcalculation;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_19.carefulcalculation.CarefulCalculation",
			"MULTI_NUMBER",
			"1/__2/__2 2/__3 1/__;;3/__;;true::1/__2/__2 2/__5 2/__;;6/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
