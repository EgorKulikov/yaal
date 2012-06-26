package on2011_9_31.taska;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_9_31.taska.TaskA",
			"SINGLE",
			"2 4 7 3;;28"))
		{
			Assert.fail();
		}
	}
}
