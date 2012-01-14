package on2011_11_26.taskb;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11_26.taskb.TaskB",
			"SINGLE",
			"1/__2/__0 1/__0 0/__;;NO/__YES/__;;true::23/__3/__23 0 23/__21 12 23/__11 13 23/__;;YES/__NO/__YES/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
