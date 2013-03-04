package on2012_03.on2012_2_25.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_25.taska.TaskA",
			"SINGLE",
			"7 2/__4 10/__4 10/__4 10/__3 20/__2 1/__2 1/__1 10/__;;3/__;;true::5 4/__3 1/__3 1/__5 3/__3 1/__3 1/__;;4/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
