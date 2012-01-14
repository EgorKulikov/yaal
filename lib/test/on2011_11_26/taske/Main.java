package on2011_11_26.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_26.taske.TaskE",
			"SINGLE",
			"2 2/__0 0/__0 0/__;;4/__;;true::2 2/__1 2/__3 4/__;;1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
