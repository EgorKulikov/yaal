package on2012_01.on2012_0_28.liquid;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_28.liquid.Liquid",
			"SINGLE",
			"1/__72/__52;;0;;true::8/__6 10 12 14 16 18 20 22/__20;;1;;true::5/__15 17 19 23 25/__6;;2;;true::2/__1 100/__98;;195/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
