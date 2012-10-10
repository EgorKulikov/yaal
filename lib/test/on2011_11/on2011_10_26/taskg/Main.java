package on2011_11.on2011_10_26.taskg;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskg.TaskG",
			"MULTI_NUMBER",
			"4 /__1 25 /__2 1 7 1 1 2 9 2 1 3 /__10 3 1 4 10 4 1 5 10 5 /__2 6 10 6 2 7 9 7 3 8 /__8 8 4 9 7 9 6 2 3 3 /__5 4 7 5 8 6 4 6 3 7 /__2 30 /__3 9 6 9 3 8 9 8 3 7 /__12 7 2 6 12 6 2 5 12 5 /__2 4 12 4 1 3 11 3 1 2 /__11 2 1 1 11 1 1 0 10 0 /__4 -1 10 -1 7 -2 10 -2 5 0 /__7 3 4 5 6 8 3 1 2 6 /__3 3 /__3 1 2 2 1 3 /__4 6 /__1 3 19 1 4 2 2 1 11 2 /__10 1;;1 10/__4 9/__7 9/__10 6/__10 3/__9 2/__7 1/__2 1/__1 2/__1 5/__2 7/__2 8/__3 9/__6 9/__12 7/__12 4/__10 -2/__7 -2/__1 0/__1 3/__3 2/__1 3/__3 1/__4 4/__1 3/__11 2/__19 1/__2 1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
