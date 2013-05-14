package on2012_05.on2012_4_8.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_8.taska.TaskA",
			"MULTI_NUMBER",
			"4/__2 20 10/__2 10 0/__4 25 25 25 25/__3 24 30 21;;Case #1/: 33.333333 66.666667/__Case #2/: 0.000000 100.000000/__Case #3/: 25.0 25.0 25.0 25.0/__Case #4/: 34.666667 26.666667 38.666667;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
