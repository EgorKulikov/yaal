package on2012_04.on2012_3_14.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_14.taskd.TaskD",
			"SINGLE",
			"100 3 50 50 0/__100 3 50 50 0/__;;1.000000/__;;true::100 3 50 50 0/__100 2 48 50 0/__;;0.888889/__;;true::100 3 50 50 0/__100 1 50 50 50/__;;0.500000/__;;true::200 30 10 100 50/__200 29 10 100 50/__;;0.45580299699278215;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
