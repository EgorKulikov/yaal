package on2011_10_29.task1869;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10_29.task1869.Task1869",
			"SINGLE",
			"3/__0 180 180/__0 0 180/__360 0 0/__;;10/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
