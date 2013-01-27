package on2012_03.on2012_2_1.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_03.on2012_2_1.taskb.TaskB",
			"SINGLE",
			"1 1/__+1/__;;Truth/__;;true::3 2/__-1/__-2/__-3/__;;Not defined/__Not defined/__Not defined/__;;true::4 1/__+2/__-3/__+4/__-1/__;;Lie/__Not defined/__Lie/__Not defined/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
