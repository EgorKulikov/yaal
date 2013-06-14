package on2011_11.on2011_10_26.taskj;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_26.taskj.TaskJ",
			"MULTI_NUMBER",
			"2/__3/__1 1/__1 2/__2 1/__4/__0 0/__1 1/__0 2/__2 1;;0.0000/__0.6503;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
