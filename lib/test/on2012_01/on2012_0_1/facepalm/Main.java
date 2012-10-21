package on2012_01.on2012_0_1.facepalm;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_1.facepalm.Facepalm",
			"SINGLE",
			"841;;2/__08.04.01/__08.04.10;;true::29244;;1/__29.02.44;;true::33247;;0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
