package on2012_07.on2012_6_30.boulevards;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_30.boulevards.Boulevards",
			"MULTI_NUMBER",
			"2/__4 5/__1 3 0/__1 2 100/__2 3 50/__0 2 40/__1 3 500/__0 1 500/__4 5/__1 3 0/__1 2 100/__2 3 50/__0 2 40/__1 3 10/__0 1 10;;100/__0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
