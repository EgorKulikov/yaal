package on2012_01.on2012_0_9.special;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_9.special.Special",
			"SINGLE",
			"11 11 2011;;11 11 2011;;true::30 10 1810;;1 11 1810;;true::26 11 2152;;25 12 2152;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
