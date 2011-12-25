package on2011_11_18.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_18.taskd.TaskD",
			"SINGLE",
			"1 3 0 -10 ISI;;11 12 13;;true::3 5 0 0 1 1 1 -1 SIJJZ;;5 4 3 4 5;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
