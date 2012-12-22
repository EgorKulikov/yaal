package on2012_03.on2012_2_1.freeshuttleservice;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_1.freeshuttleservice.FreeShuttleService",
			"MULTI_NUMBER",
			"3/__2/__3/__4/__;;1/__2/__2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
