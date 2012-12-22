package on2012_05.on2012_4_5.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_5.taska.TaskA",
			"MULTI_NUMBER",
			"3/__2/__100 90 20/__200 195 70/__4/__123 123 25/__90 8 12/__50 50 10/__10 120 4/__3/__100 90 20/__200 195 70/__100 60 20;;Fire laser at 40 degrees./__2 burst balloons./__No balloon burst./__Fire laser at 30 degrees./__Fire laser at 40 degrees./__3 burst balloons.;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
