package on2011_10.on2011_9_30.printing;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_30.printing.Printing",
			"SINGLE",
			"4 5/__5 5/__2 3/__5 10/__1 1;;4::1 2/__1 3;;-1"))
		{
			Assert.fail();
		}
	}
}
