package on2012_0_28.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_28.taske.TaskE",
			"SINGLE",
			"2 3/__47/__count/__switch 1 2/__count/__;;2/__1/__;;true::3 5/__747/__count/__switch 1 1/__count/__switch 1 3/__count/__;;2/__3/__2/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
