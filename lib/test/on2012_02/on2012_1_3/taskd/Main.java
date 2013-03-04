package on2012_02.on2012_1_3.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_3.taskd.TaskD",
			"SINGLE",
			"1 3/__;;0.500000000/__;;true::5 5/__;;0.658730159/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
