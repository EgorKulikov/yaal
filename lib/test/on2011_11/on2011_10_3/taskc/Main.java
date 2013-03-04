package on2011_11.on2011_10_3.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_3.taskc.TaskC",
			"SINGLE",
			"1 2 1/__1 2/__;;()/__;;true::2 3 1/__1 2 3/__4 5 6/__;;(()/__())/__;;true::3 2 2/__3 6/__1 4/__2 5/__;;()/__)(/__()/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
