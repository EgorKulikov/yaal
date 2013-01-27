package on2011_11.on2011_10_26.taskh;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskh.TaskH",
			"MULTI_NUMBER",
			"6 /__1 8 /__5 10/__8 9 /__11 6 /__10 2 /__6 0 /__1 1 /__0 4 /__2 8 /__2 4 /__3 10 /__13 7 /__10 -3 /__0 0 /__3 3 /__1 3 /__3 1 /__1 1 /__4 3 /__1 4 /__4 1 /__1 1 /__5 4 /__0 6 /__2 3 /__3 0 /__1 3 /__6 6 /__1 3 /__3 3 /__4 2 /__3 1 /__1 1 /__0 2;;1 9 /__9 4 7 /__8 3 8 /__7 2 9 /__6 2 10 /__5 1 10 /__4 1 10 /__3 1 10 /__2 1 9 /__1 2 7 /__2 12 /__9 3 6 /__8 3 9 /__7 3 12 /__6 2 12 /__5 2 12 /__4 2 12 /__3 1 11 /__2 1 11 /__1 1 11 /__0 1 10 /__-1 4 10 /__-2 7 10 /__3 0 /__4 1 /__2 2 2 /__5 2 /__4 1 1 /__2 2 2 /__6 1 /__2 1 3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
