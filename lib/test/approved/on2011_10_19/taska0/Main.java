package approved.on2011_10_19.taska0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"approved.on2011_10_19.taska0.TaskA",
			"SINGLE",
			"4/__0 0/__0 1/__1 0/__1 1;;1/__2/__4/__3/__;;true::3/__0 0/__0 1/__0 2;;No solution;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
