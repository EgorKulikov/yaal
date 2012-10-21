package on2012_02.on2012_1_12.task1;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_02.on2012_1_12.task1.Task1",
			"MULTI_NUMBER",
			"2/__4/__1 2/__1 1/__1 4/__1 3/__4/__3 2 3 4/__3 1 3 4/__3 1 2 4/__3 1 2 3/__;;YES/__NO;;true::1/__6/__2 2 3/__3 1 3 4/__2 1 2/__3 2 5 6/__2 4 6/__2 4 5/__;;YES/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
