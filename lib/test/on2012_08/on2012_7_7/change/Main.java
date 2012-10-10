package on2012_08.on2012_7_7.change;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_7.change.Change",
			"MULTI_NUMBER",
			"2/__2 3/__3/__0 10/__1 0/__6 1/__3 4/__2 6/__2 2 2/__4 0 0/__1 10 1/__7 0 3;;16/__49;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
