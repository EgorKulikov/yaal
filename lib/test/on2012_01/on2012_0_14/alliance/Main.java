package on2012_01.on2012_0_14.alliance;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_14.alliance.Alliance",
			"SINGLE",
			"5 4/__1 2/__3 2/__4 5/__4 5;;6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
