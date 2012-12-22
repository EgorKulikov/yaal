package on2012_07.on2012_6_20.task1590;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_20.task1590.Task1590",
			"SINGLE",
			"aaba/__;;8/__;;true::aaaaaaa;;7;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
