package on2012_07.on2012_6_23.taske;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_07.on2012_6_23.taske.TaskE",
			"SINGLE",
			"5/__1 2/__2 3/__3 4/__4 5/__;;3/__1 3/__2 2/__3 1/__;;true::10/__1 2/__2 3/__3 4/__5 6/__6 7/__7 4/__8 9/__9 10/__10 4/__;;6/__1 8/__2 7/__3 6/__6 3/__7 2/__8 1/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
