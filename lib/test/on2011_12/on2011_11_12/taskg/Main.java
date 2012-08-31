package on2011_12.on2011_11_12.taskg;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_12.taskg.TaskG",
			"SINGLE",
			"5/__100 25 300 400 12000/__4/__10 25 25 500/__;;10 25 100 300 400 500 12000/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
