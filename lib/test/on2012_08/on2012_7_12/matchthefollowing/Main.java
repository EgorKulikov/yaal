package on2012_08.on2012_7_12.matchthefollowing;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_12.matchthefollowing.MatchTheFollowing",
			"MULTI_NUMBER",
			"2/__2/__4 10/__6 2/__2/__5 8/__8 4/__;;1/__1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
