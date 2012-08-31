package on2012_08.on2012_7_18.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_18.taska.TaskA",
			"SINGLE",
			"2/__2 1/__1 2/__;;1/__;;true::2/__2 1/__4 1/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
