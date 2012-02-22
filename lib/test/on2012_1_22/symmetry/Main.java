package on2012_1_22.symmetry;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_22.symmetry.Symmetry",
			"SINGLE",
			"4/__0 0/__0 1/__1 0/__1 1;;4;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
