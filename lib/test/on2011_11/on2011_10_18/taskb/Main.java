package on2011_11.on2011_10_18.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_18.taskb.TaskB",
			"SINGLE",
			"4/__3 2 1 6;;3;;true::4/__1 2 3 4;;-1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
