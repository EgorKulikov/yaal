package on2011_12.on2011_11_12.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_12.taske.TaskE",
			"SINGLE",
			"3/__;;1 2 3/__1 3 2/__2 1 3/__2 3 1/__3 1 2/__3 2 1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
