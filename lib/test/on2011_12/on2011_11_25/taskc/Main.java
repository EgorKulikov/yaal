package on2011_12.on2011_11_25.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_25.taskc.TaskC",
			"SINGLE",
			"1 1/__2 2 50 50/__1 1/__;;0.5/__;;true::2 1/__2 2 50 50/__4 2 50 50/__3 1/__;;0.25/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
