package on2011_10.on2011_9_27.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taske.TaskE",
			"SINGLE",
			"3 3/__2 7 4/__2 1 2/__1 2/__2 2 3;;4"))
		{
			Assert.fail();
		}
	}
}
