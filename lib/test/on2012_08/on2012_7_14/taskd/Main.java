package on2012_08.on2012_7_14.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_14.taskd.TaskD",
			"SINGLE",
			"7/__3 1 6 7/__4 3 5 2 9/__2 8 1/__4 3 7 6 4/__3 2 5 9/__3 6 3 8/__3 4 2 9/__;;6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
