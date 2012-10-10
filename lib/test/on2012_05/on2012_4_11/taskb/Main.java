package on2012_05.on2012_4_11.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_11.taskb.TaskB",
			"SINGLE",
			"4 2 3/__0 1 5 6/__2 0 3 6/__1 3 0 1/__6 6 7 0/__0 3 5 6/__2 0 1 6/__1 3 0 2/__6 6 7 0/__1 4 2/__1 4 1/__1 4 3/__;;3/__4/__3/__;;true::4 2 3/__0 7 3 3/__8 0 10 5/__1 1 0 4/__8 9 2 0/__0 3 3 9/__7 0 4 9/__3 8 0 4/__4 8 9 0/__2 3 3/__2 1 3/__1 2 2/__;;4/__5/__3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
