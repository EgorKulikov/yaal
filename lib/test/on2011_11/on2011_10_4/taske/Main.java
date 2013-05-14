package on2011_11.on2011_10_4.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2011_11.on2011_10_4.taske.TaskE",
			"MULTI_NUMBER",
			"2/__4/__1260;;Scenario #1/:/__3/__/__Scenario #2/:/__113/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
