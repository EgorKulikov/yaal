package on2012_06.on2012_5_16.taskf;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_16.taskf.TaskF",
			"SINGLE",
			"3 2/__1 1 -1 2/__3 2 1 3/__6 1 0 2;;6;;true::1 2/__1 1 -2 2;;Impossible;;true::2 3/__0 1 1 2/__1 1 0 2/__;;5;;true::0 100000/__;;100000/__;;true::1 1/__1 100 -100 100/__;;1;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
