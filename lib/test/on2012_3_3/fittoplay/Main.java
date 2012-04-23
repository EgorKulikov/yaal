package on2012_3_3.fittoplay;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_3.fittoplay.FitToPlay",
			"MULTI_NUMBER",
			"3/__6/__3 7 1 4 2 4/__5/__5 4 3 2 1/__5/__4 3 2 2 3/__;;4/__UNFIT/__1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
