package on2012_07.on2012_6_22.cielandmap;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_22.cielandmap.CielAndMap",
			"MULTI_NUMBER",
			"1/__6/__3 2/__5 2/__3 4/__6 3/__6 6/__5 6/__;;5.0000000000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
