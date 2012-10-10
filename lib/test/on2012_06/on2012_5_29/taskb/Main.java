package on2012_06.on2012_5_29.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_06.on2012_5_29.taskb.TaskB",
			"SINGLE",
			"2 3/__3 4 5/__3 9 1/__;;392/__1 1/__;;true::3 4/__1 0 0 0/__0 0 3 0/__0 0 5 5/__;;240/__2 3/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
