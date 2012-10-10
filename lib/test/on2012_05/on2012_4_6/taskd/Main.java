package on2012_05.on2012_4_6.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_6.taskd.TaskD",
			"MULTI_NUMBER",
			"2/__3 1 10 2/__5 0 4 3/__;;0/__0/__;;true::3/__1 0 100 103/__2 1 3 10001/__3 1 2 10001/__;;2/__1843/__410/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
