package on2011_11.on2011_10_2.taskh;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_2.taskh.TaskH",
			"SINGLE",
			"5 5/__11111/__10001/__10001/__10001/__11111/__4 4 10/__2 2 1/__2 3 2/__3 2 3/__3 3 4;;10;;true::5 5/__11111/__10001/__10111/__10101/__11111/__4 4 10/__2 2 1/__2 2 2/__2 2 3/__2 2 4;;0;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
