package on2012_3_25.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_3_25.taske.TaskE",
			"SINGLE",
			"2 3/__1 2/__2 3/__;;2/__;;true::1 2/__2 2/__;;1/__;;true::6 6/__2 1/__3 2/__2 5/__3 3/__5 1/__2 1/__;;20/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
