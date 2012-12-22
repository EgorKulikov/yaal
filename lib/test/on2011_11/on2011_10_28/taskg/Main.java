package on2011_11.on2011_10_28.taskg;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_28.taskg.TaskG",
			"SINGLE",
			"1 8 2009/__;;3 8 2009/__;;true::30 12 2009/__;;1 1 2010/__;;true::28 2 2008/__;;1 3 2008/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
