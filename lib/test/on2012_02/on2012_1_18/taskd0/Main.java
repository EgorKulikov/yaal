package on2012_02.on2012_1_18.taskd0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_18.taskd0.TaskD",
			"SINGLE",
			"4 5 7 4 9 1 2 3 4;;7 2 4 1 3;;true::10 9 5 1 2 6 7 4 18 20 12 10 40 20 30 50 70 80 100 1000 500;;3010 100 80 10 40 50 1000 20 70 500 30;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
