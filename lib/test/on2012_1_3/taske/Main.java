package on2012_1_3.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_1_3.taske.TaskE",
			"SINGLE",
			"2 3/__3 3 7 2/__3 4 1 5/__;;15/__;;true::1 3/__4 4 3 1 2/__;;9/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
