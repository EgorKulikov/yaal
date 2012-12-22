package on2012_04.on2012_3_25.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_25.taskd.TaskD",
			"SINGLE",
			"abcdabcd/__abcdabcdabcdabcd/__;;2/__;;true::aaa/__aa/__;;1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
