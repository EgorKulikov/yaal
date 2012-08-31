package on2012_08.on2012_7_14.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_14.taskb.TaskB",
			"SINGLE",
			"5 4/__1 2/__2 4/__5 3/__1 4/__;;1;;true::6 2/__1 4/__3 4/__;;0;;true::6 6/__1 2/__2 3/__3 1/__4 5/__5 6/__6 4/__;;2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
