package on2012_07.on2012_6_30.bindit;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_30.bindit.BindIt",
			"MULTI_EOF",
			"q..rj../__.....z./__.d...../__.e....t/__.b...u./__kc...../__....s../__/__.s..a../__....b.c/__.d.e.f./__......./__...h.../__......g/__.ij.k../__/__0;;suctzbjkreqd/__impossible!;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
