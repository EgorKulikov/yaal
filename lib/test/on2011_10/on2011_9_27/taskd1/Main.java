package on2011_10.on2011_9_27.taskd1;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taskd1.TaskD",
			"SINGLE",
			"4 7/__1 4/__6 9/__4 7/__3 5/__;;1/__::2 7/__40 45/__47 74/__;;2/__"))
		{
			Assert.fail();
		}
	}
}
