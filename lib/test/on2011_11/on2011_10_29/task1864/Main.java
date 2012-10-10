package on2011_11.on2011_10_29.task1864;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_29.task1864.Task1864",
			"SINGLE",
			"3/__10 10 10/__;;33 33 33/__;;true::2/__10 0/__;;100 0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
