package on2012_07.on2012_6_31.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_31.taske.TaskE",
			"SINGLE",
			"1 1/__1/__1/__;;1/__;;true::1 2/__1/__2 1/__;;2/__;;true::3 3/__2 3 1/__1 2 3/__;;0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
