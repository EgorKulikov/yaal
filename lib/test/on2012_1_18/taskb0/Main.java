package on2012_1_18.taskb0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_18.taskb0.TaskB",
			"SINGLE",
			"8 1 -1 4 3 8 -2 3 -3;;6;;true::15 1 2 -1 2 3 4 5 6 1 4 8 3 -1 -2 1;;8;;true::3/__1 2 3/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
