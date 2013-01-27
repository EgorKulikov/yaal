package on2011_12.on2011_11_12.tasks;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_12.tasks.TaskS",
			"SINGLE",
			"2 4/__;;1 2/__;;true::12 15/__;;4 5/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
