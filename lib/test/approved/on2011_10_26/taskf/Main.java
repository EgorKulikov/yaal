package approved.on2011_10_26.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"approved.on2011_10_26.taskf.TaskF",
			"SINGLE",
			"4 6 2/__111000/__111100/__011011/__000111/__;;6/__;;true::5 5 4/__11111/__11111/__11111/__11111/__11111/__;;9/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
