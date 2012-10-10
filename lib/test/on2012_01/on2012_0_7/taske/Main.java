package on2012_01.on2012_0_7.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_7.taske.TaskE",
			"SINGLE",
			"3 2 1000/__3 1 2/__;;8/__;;true::2 3 1000/__2 2/__;;24/__;;true::1 1 1000/__5/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
