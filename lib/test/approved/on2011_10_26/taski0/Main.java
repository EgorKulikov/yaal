package approved.on2011_10_26.taski0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"approved.on2011_10_26.taski0.TaskI",
			"MULTI_NUMBER",
			"3/__6/__1 2 1 3 1 2/__4/__2 4 2 4/__9/__10 2 2 10 3 4 5 4 3;;2/__1/__2;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
