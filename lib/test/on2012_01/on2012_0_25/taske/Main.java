package on2012_01.on2012_0_25.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_25.taske.TaskE",
			"SINGLE",
			"3 3/__2 3/__3 2/__3 3/__;;3/__1 2 3 /__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
