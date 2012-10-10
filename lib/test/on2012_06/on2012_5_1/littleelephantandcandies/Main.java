package on2012_06.on2012_5_1.littleelephantandcandies;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_1.littleelephantandcandies.LittleElephantAndCandies",
			"MULTI_NUMBER",
			"2/__2 3/__1 1/__3 7/__4 2 2/__;;Yes/__No/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
