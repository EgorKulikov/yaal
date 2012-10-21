package on2011_11.on2011_10_26.taskb0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskb0.TaskB",
			"MULTI_NUMBER",
			"3/__GGGBWWRRWRR/__XAABCBAXAAAA/__CCC;;2/__4/__1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
