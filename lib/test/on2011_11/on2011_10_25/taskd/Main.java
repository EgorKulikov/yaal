package on2011_11.on2011_10_25.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_25.taskd.TaskD",
			"SINGLE",
			"4/__1 3/__4 3/__4 2/__1 2/__;;0 0 0 0 ;;true::6/__1 2/__3 4/__6 4/__2 3/__1 3/__3 5/__;;0 0 0 1 1 2 ;;true::4/__1 2/__2 3/__3 4/__2 4/__;;1 0 0 0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
