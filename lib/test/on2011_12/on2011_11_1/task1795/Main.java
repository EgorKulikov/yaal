package on2011_12.on2011_11_1.task1795;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_12.on2011_11_1.task1795.Task1795",
			"SINGLE",
			"3/__2 of sweets/__4 of milk/__1 of sausage/__4/__2 of milk/__3 of sweets/__3 of milk/__1 of cheese/__;;6;;true::1/__10 of milk/__3/__2 of milk/__10 of milk/__10 of milk;;5;;true::1/__2 of milk/__3/__10 of milk/__1 of milk/__10 of milk;;6;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
