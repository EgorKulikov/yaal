package on2012_02.on2012_1_25.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_25.taskb.TaskB",
			"SINGLE",
			"10 10/__+ 6/__+ 10/__+ 5/__- 10/__- 5/__- 6/__+ 10/__+ 3/__+ 6/__+ 3/__;;Success/__Conflict with 6/__Success/__Already off/__Success/__Success/__Success/__Success/__Conflict with 10/__Already on/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
