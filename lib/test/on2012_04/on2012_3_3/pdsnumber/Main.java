package on2012_04.on2012_3_3.pdsnumber;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_3.pdsnumber.PDSNumber",
			"MULTI_EOF",
			"1/__20/__0/__;;1/__66/__;;true::1000000000/__0/__;;1439423451;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
