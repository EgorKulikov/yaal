package on2011_10.on2011_9_30.swords;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_30.swords.Swords",
			"SINGLE",
			"6 3/__2 10 6 0 5 2;;5 0 2 3 2 5::4 3/__10 1 1 1;;-1"))
		{
			Assert.fail();
		}
	}
}
