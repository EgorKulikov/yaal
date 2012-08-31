package on2011_11.on2011_10_26.taske0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taske0.TaskE",
			"MULTI_NUMBER",
			"5 /__2 2 /__1 2 /__2 1 /__2 0 /__1 2 /__2 1/__ /__2 1/__ /__1 2/__ /__2 1/__ /__1 2/__ /__2 2/__ /__4 2/__ /__4 3/__ /__1 2/__ /__3 4/__ /__2 1;;1.0000/__0.0000/__1.0000/__0.0000/__1.0000/__;;true::1/__3 2/__5 1/__1 5/__2 2/__;;1.5000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
