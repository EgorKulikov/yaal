package on2011_11.on2011_10_2.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_2.taskb.TaskB",
			"SINGLE",
			"2 2 1 1/__3/__5 2 2 1/__3 3 3 1.5/__8 8 8 1;;2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
