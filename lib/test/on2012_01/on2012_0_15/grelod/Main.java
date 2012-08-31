package on2012_01.on2012_0_15.grelod;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_15.grelod.Grelod",
			"SINGLE",
			"4/__Alex/_KPR JKeeJ1e30/__JKeeJ1e30 anonymous/__anonymous Alex/_KPR/__natalia natalia;;2/__Alex/_KPR <=> JKeeJ1e30/__Alex/_KPR <=> anonymous;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
