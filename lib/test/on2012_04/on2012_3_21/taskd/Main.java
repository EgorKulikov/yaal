package on2012_04.on2012_3_21.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_21.taskd.TaskD",
			"SINGLE",
			"4 3 2/__1 1 1 1/__1 1 1;;0 1 1 0;;true::3 1 5/__1 2 3/__4;;0 1 2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
