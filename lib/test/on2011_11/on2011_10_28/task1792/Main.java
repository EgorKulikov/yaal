package on2011_11.on2011_10_28.task1792;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.task1792.Task1792",
			"SINGLE",
			"0 1 0 1 1 0 1/__;;0 1 0 0 1 0 1/__;;true::1 1 1 1 1 1 1/__;;1 1 1 1 1 1 1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
