package on2011_10.on2011_9_29.taskc;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_29.taskc.TaskC",
			"MULTI_NUMBER",
			"3/__BGWBBGGGBBWGBBGWBB/__GBBWBBWBBB/__BBBBBBBBBBBBBBB;;Case 1/: 9/__Case 2/: -1/__Case 3/: 8"))
		{
			Assert.fail();
		}
	}
}
