package on2011_11.on2011_10_26.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskb.TaskB",
			"MULTI_NUMBER",
			"3/__1 6/__2 5 1 3 3 7/__2 6/__1 2 3 4 5 6/__3 20/__1 1 2 1 1 2 1 1 2 1/__1 2 1 1 2 1 1 2 1 1/__;;1 7/__2 21/__3 2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
