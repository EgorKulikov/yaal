package on2012_04.on2012_3_23.subsets;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_23.subsets.Subsets",
			"SINGLE",
			"4/__1 2 3 4/__;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
