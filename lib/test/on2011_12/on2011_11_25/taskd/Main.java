package on2011_12.on2011_11_25.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_25.taskd.TaskD",
			"SINGLE",
			"2 2/__RL/__LR/__;;LOSE/__;;true::2 2/__RR/__RR/__;;WIN/__;;true::1 5/__XXXXX/__;;WIN;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
