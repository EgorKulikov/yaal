package on2011_12.on2011_11_26.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_26.taskf.TaskF",
			"SINGLE",
			"10 2 4/__2 3/__2 2/__1 2 3 4/__;;0 2 1 0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
