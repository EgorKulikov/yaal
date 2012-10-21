package on2011_10.on2011_9_27.taskc0;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_10.on2011_9_27.taskc0.TaskC",
			"MULTI_NUMBER",
			"3/__14 1000/__cup/__russia/__7 123/__russian/__codecup/__7 15/__codec/__decup;;752/__0/__1::3/__3 100/__a/__a/__5 100/__bab/__bab/__10 10000/__bab/__babab;;26/__1/__1351/__"))
		{
			Assert.fail();
		}
	}
}
