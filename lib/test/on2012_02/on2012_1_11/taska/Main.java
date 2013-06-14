package on2012_02.on2012_1_11.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_11.taska.TaskA",
			"SINGLE",
			"5/__1 1 1 1 2 2 3 2 2 1 1 1/__;;2/__;;true::0/__0 0 0 0 0 0 0 1 1 2 3 0/__;;0/__;;true::11/__1 1 4 1 1 5 1 1 4 1 1 1/__;;3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
