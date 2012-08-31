package on2012_04.on2012_3_11.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_04.on2012_3_11.taska.TaskA",
			"SINGLE",
			"4 3/__1 0 0 2/__1 2/__2 3/__3 4/__;;1/__1/__1/__1/__;;true::3 1/__1 0 2/__1 3/__;;1/__0/__1/__;;true::3 1/__2 0 1/__1 3/__;;0/__0/__0/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
