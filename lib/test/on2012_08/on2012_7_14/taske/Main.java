package on2012_08.on2012_7_14.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_08.on2012_7_14.taske.TaskE",
			"SINGLE",
			"10 5 6/__3 2 0 5 6 1/__;;5;;true::7 6 4/__3 5 0 4/__;;1;;true::257 0 3/__0 0 256/__;;3;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
