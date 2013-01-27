package on2012_04.on2012_3_14.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_14.taskc.TaskC",
			"SINGLE",
			"1/__5 10/__2/__3 6/__;;70;;true::2/__3 8/__5 10/__1/__20/__;;74;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
