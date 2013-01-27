package on2012_06.on2012_5_16.taskb0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_16.taskb0.TaskB",
			"SINGLE",
			"4 1 4/__1 2 10/__1 4 10/__3 4 5;;15;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
