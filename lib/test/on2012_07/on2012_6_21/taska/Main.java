package on2012_07.on2012_6_21.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_21.taska.TaskA",
			"SINGLE",
			"2 47/__;;12/__;;true::47 1024/__;;98/__;;true::1 11/__;;10/__;;true::1 10/__;;9;;true::1 252/__;;34/__;;true::1 1000000000000000000/__;;100000000000000008;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
