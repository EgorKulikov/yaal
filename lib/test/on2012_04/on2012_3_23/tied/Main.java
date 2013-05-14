package on2012_04.on2012_3_23.tied;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_23.tied.Tied",
			"SINGLE",
			"2 10 6 1/__2 3/__2 1/__6 1/__2 4/__1 1/__2 0/__3 1/__1 3/__5 4/__3 0/__0 1/__3 2/__6 1;;1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
