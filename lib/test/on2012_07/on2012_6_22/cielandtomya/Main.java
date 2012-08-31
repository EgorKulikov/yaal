package on2012_07.on2012_6_22.cielandtomya;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_22.cielandtomya.CielAndTomya",
			"MULTI_NUMBER",
			"2/__3 3/__1 2 3/__2 3 6/__1 3 7/__3 3/__1 2 3/__2 3 6/__1 3 9/__;;1/__2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
