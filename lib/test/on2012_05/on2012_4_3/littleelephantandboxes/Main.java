package on2012_05.on2012_4_3.littleelephantandboxes;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_3.littleelephantandboxes.LittleElephantAndBoxes",
			"MULTI_NUMBER",
			"2/__2 2/__2 50/__2 100/__2 0/__2 0/__2 2/__2 100/__2 50/__0 2/__0 1/__;;1.5000/__0.5000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
