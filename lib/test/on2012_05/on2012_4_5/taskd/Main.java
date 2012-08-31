package on2012_05.on2012_4_5.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_5.taskd.TaskD",
			"MULTI_NUMBER",
			"5/__25/__0.5/__25/__0.4/__25/__0.6/__15/__0.8/__10/__0.95;;0.50/__0.08/__0.92/__1.00/__1.00;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
