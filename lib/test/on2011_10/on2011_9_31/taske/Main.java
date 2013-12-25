package on2011_10.on2011_9_31.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_31.taske.TaskE",
			"MULTI_NUMBER",
			"3/__3 1 2/__1 0 0/__9 5 12;;01/__1/__10101101"))
		{
			Assert.fail();
		}
	}
}
