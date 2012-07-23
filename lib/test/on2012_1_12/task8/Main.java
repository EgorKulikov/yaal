package on2012_1_12.task8;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_12.task8.Task8",
			"SINGLE",
			"6/__1 2/__1 3/__1 4/__5 3/__3 6;;2;;true::4/__1 2/__1 3/__2 4/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
