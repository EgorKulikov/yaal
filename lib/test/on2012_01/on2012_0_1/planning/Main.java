package on2012_01.on2012_0_1.planning;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_1.planning.Planning",
			"SINGLE",
			"4 5/__1 2/__1 3/__2 4/__3 4/__2 3;;2/__2 3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
