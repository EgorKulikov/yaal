package on2012_06.on2012_5_13.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_13.taska.TaskA",
			"MULTI_NUMBER",
			"3/__4/__1 1 1 1/__50 0 20 20/__3/__100 10 1/__0 50 0/__3/__100 80 50/__40 20 80;;Case #1/: 0 2 3 1/__Case #2/: 1 0 2/__Case #3/: 2 0 1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
