package on2011_11.on2011_10_19.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_19.taskf.TaskF",
			"SINGLE",
			"3 2 10 2 6 5 4 3 1 6 1 3 0 10;;3 2 -11;;true::4 2 3 2 0 3 4 3 4 1 3 0 4 1 4 5;;-8 -13 -18;;true::6 7 17 5 26 4 5 5 12 4 8 1 18 2 3 31 3 4 11 5 4 19 3 5 23 2 6 15 1 5 19 1 3 10 4;;27 59 56 69 78 81 82 58;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
