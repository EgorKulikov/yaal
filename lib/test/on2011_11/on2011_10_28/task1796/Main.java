package on2011_11.on2011_10_28.task1796;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.task1796.Task1796",
			"SINGLE",
			"0 2 0 0 0 0/__10/__;;5/__6 7 8 9 10/__;;true::1 2 0 0 0 0/__10/__;;1/__11;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
