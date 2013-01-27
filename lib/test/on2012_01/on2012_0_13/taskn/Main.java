package on2012_01.on2012_0_13.taskn;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_13.taskn.TaskN",
			"MULTI_EOF",
			"3/__10 10 2 Jill/__5 3 10 Will/__5 5 10 Bill/__4/__2 4 10 Cam/__4 3 7 Sam/__8 11 1 Graham/__6 2 7 Pam/__-1/__;;Bill Will/__Graham Cam/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
