package on2012_4_5.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_4_5.taskb.TaskB",
			"MULTI_NUMBER",
			"4/__25/__4/__10 12 5 7/__925/__10/__45 15 120 500 235 58 6 12 175 70/__120/__5/__25 25 25 25 25/__0/__2/__13 567;;NO/__YES/__NO/__YES;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
