package on2012_06.on2012_5_26.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_26.taskc.TaskC",
			"SINGLE",
			"10 0 1/__-10 0 2 8/__;;9.584544103;;true::50 60 10/__50 60 20 40/__;;0.000000000;;true::10 0 1/__11 0 10000 1/__;;0.0001/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
