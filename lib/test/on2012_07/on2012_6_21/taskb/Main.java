package on2012_07.on2012_6_21.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_21.taskb.TaskB",
			"SINGLE",
			"3/__4 7/__4 7/__7 4/__;;0/__;;true::5/__4 7/__7 4/__2 11/__9 7/__1 1/__;;2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
