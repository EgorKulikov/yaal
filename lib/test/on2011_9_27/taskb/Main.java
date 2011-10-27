package on2011_9_27.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_9_27.taskb.TaskB",
			"SINGLE",
			"7 4/__4727447/__;;4427477/__::4 2/__4478/__;;4478/__::7 1000000000/__4727447;;4427477"))
		{
			Assert.fail();
		}
	}
}
