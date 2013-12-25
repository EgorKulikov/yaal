package on2011_11.on2011_10_26.taskh0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskh0.TaskH",
			"MULTI_NUMBER",
			"3/__7 10/__10 16/__10 7;;0/__5/__6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
