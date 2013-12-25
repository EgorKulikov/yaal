package on2012_06.on2012_5_3.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_3.taskf.TaskF",
			"MULTI_EOF",
			"7 3/__6 3 4 -1 1 -5 1/__0 1 1 0 0 5/__2 1/__-1 1/__0/__3 3/__1 2 3/__0 0/__8 8/__1 2 3 4 5 6 7 8/__0 1 2 3 4 5 6/__4 4/__-27 -45 -67 -98/__2 0 2/__0 0;;6/__1/__5/__8/__-27;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
