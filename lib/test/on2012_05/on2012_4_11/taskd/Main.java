package on2012_05.on2012_4_11.taskd;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_05.on2012_4_11.taskd.TaskD",
			"SINGLE",
			"1 3 2/__5 2/__5/__1/__2/__3/__4/__5/__;;8/__9/__12/__12/__12/__;;true::5 3 7/__10 1 1 8 900000005 1000000000/__3/__1/__10/__1000000000/__;;1900000040/__1900000040/__2900000030/__;;true::4 1 1/__1 1 1 1 1/__3/__1/__2/__3/__;;9/__11/__11/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
