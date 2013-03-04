package on2012_01.on2012_0_13.tasko;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_13.tasko.TaskO",
			"MULTI_EOF",
			"1 4 3 2 9 7 18 22 0/__2 4 8 10 0/__7 5 11 13 1 3 0/__-1/__;;3/__2/__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
