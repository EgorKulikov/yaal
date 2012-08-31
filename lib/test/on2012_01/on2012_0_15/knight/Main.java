package on2012_01.on2012_0_15.knight;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_15.knight.Knight",
			"MULTI_NUMBER",
			"3/__6 7/__2 1 1/__3 2 1/__6 3 1/__4 1 20/__3 4 20/__5 2 20/__6 5 20/__2/__3 2/__5 5/__2 1 1/__3 1 4/__4 2 1/__4 3 4/__5 4 1/__1/__2/__5 5/__2 1 1/__3 1 3/__4 2 1/__4 3 4/__5 4 2/__1/__4;;82/__12/__-1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
