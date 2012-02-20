package on2012_0_19.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_0_19.taske.TaskE",
			"MULTI_NUMBER",
			"1/__3 2 5/__2 1/__3 1/__4 1/__1 3/__1 4/__3 2;;11;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
