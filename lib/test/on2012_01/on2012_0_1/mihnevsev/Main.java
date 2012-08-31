package on2012_01.on2012_0_1.mihnevsev;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_1.mihnevsev.Mihnevsev",
			"SINGLE",
			"3 1 2/__0 2 3/__0 1 1/__0 1 1;;1 2/__2 2/__2 1/__3 1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
